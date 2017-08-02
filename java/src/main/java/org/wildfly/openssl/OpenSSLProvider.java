/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.openssl;

import java.security.Provider;
import java.security.Security;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Stuart Douglas
 */
public final class OpenSSLProvider extends Provider {

    private static AtomicBoolean registered = new AtomicBoolean();

    public static final OpenSSLProvider INSTANCE = new OpenSSLProvider();

    public OpenSSLProvider() {
        super("openssl", 1.0, "OpenSSL provider");
        put("SSLContext.openssl.TLS", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLSContextSpi.class.getSimpleName());
        put("SSLContext.openssl.TLSv1", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLS_1_0_ContextSpi.class.getSimpleName());
        put("SSLContext.openssl.TLSv1.1", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLS_1_1_ContextSpi.class.getSimpleName());
        put("SSLContext.openssl.TLSv1.2", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLS_1_2_ContextSpi.class.getSimpleName());
        put("SSLContext.TLS", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLSContextSpi.class.getSimpleName());
        put("SSLContext.TLSv1", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLS_1_0_ContextSpi.class.getSimpleName());
        put("SSLContext.TLSv1.1", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLS_1_1_ContextSpi.class.getSimpleName());
        put("SSLContext.TLSv1.2", OpenSSLContextSPI.class.getName() + "$" + OpenSSLContextSPI.OpenSSLTLS_1_2_ContextSpi.class.getSimpleName());
    }

    public static void register() {
        if (registered.compareAndSet(false, true)) {
            Security.addProvider(INSTANCE);
        }
    }
    public static void registerFirst() {
        if (registered.compareAndSet(false, true)) {
            Security.insertProviderAt(INSTANCE, 1);
        }
    }
}
