import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinHelperKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class WebViewNavigationDelegate: NSObject, WKNavigationDelegate {
    let onFinish: () -> Void

    init(onFinish: @escaping () -> Void) {
        self.onFinish = onFinish
    }

    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.3) {
            self.onFinish()
        }
    }
}

func exportPDF(from html: String, completion: @escaping (URL?) -> Void) {
    let webView = WKWebView(frame: CGRect(x: 0, y: 0, width: 595, height: 842))
    let delegate = WebViewNavigationDelegate {
        let config = UIGraphicsPDFRendererFormat()
        let pageRect = CGRect(x: 0, y: 0, width: 595, height: 842)
        let renderer = UIGraphicsPDFRenderer(bounds: pageRect, format: config)

        let data = renderer.pdfData { ctx in
            ctx.beginPage()
            webView.drawHierarchy(in: webView.bounds, afterScreenUpdates: true)
        }

        let url = FileManager.default.temporaryDirectory.appendingPathComponent("quiz.pdf")
        try? data.write(to: url)
        completion(url)
    }

    webView.navigationDelegate = delegate
    webView.loadHTMLString(html, baseURL: nil)
}