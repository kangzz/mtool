package com.kangzz.mtool.http.ssl;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 证书管理
 * @author Looly
 *
 */
public class DefaultTrustManager implements X509TrustManager {

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
}
