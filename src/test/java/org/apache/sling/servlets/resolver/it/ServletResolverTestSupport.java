/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.servlets.resolver.it;

import org.apache.sling.testing.paxexam.TestSupport;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;

import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.cm.ConfigurationAdminOptions.newConfiguration;
import static org.apache.sling.testing.paxexam.SlingOptions.slingQuickstartOakTar;

public class ServletResolverTestSupport extends TestSupport {
    protected final int httpPort = findFreePort();

    @Configuration
    public Option[] configuration() {
        return new Option[]{
            baseConfiguration(),
            slingQuickstartOakTar(workingDirectory(), httpPort),
            testBundle("bundle.filename"),
            wrappedBundle(mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.servlet-helpers").versionAsInProject()),
            junitBundles(),
            newConfiguration("org.apache.sling.jcr.base.internal.LoginAdminWhitelist")
                .put("whitelist.bundles.regexp", "^PAXEXAM.*$")
                .asOption(),
        };
    }
}
