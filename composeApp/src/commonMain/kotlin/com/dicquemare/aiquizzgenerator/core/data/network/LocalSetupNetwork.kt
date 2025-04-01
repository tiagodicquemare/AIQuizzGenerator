package com.dicquemare.aiquizzgenerator.core.data.network

class NetworkConfig {

    companion object {
        private val localIp = "192.168.129.12"
        val LOCAL_IP = "http://$localIp:5001/api"  // Change when using different wi-fi
    }
}