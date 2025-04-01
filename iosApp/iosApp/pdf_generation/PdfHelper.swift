import Foundation
import WebKit
import UIKit

@objc class PdfHelper: NSObject {
    @objc static func exportPdf(
        html: String,
        fileName: String,
        completion: @escaping (NSData?) -> Void
    ) {
        let webView = WKWebView(frame: CGRect(x: 0, y: 0, width: 595, height: 842))

        let delegate = WebViewNavigationDelegate {
            let config = UIGraphicsPDFRendererFormat()
            let pageRect = CGRect(x: 0, y: 0, width: 595, height: 842)
            let renderer = UIGraphicsPDFRenderer(bounds: pageRect, format: config)

            let data = renderer.pdfData { ctx in
                ctx.beginPage()
                webView.drawHierarchy(in: webView.bounds, afterScreenUpdates: true)
            }

            completion(data as NSData)
        }

        webView.navigationDelegate = delegate
        webView.loadHTMLString(html, baseURL: nil)
    }
}
