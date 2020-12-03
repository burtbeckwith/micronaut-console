/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 Agorapulse.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agorapulse.micronaut.console.advisors;

import com.agorapulse.micronaut.console.ConsoleConfiguration;
import com.agorapulse.micronaut.console.Script;
import com.agorapulse.micronaut.console.SecurityAdvisor;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;

import javax.inject.Singleton;

@Singleton
public class CloudAdvisor implements SecurityAdvisor {

    private final ConsoleConfiguration configuration;
    private final ApplicationContext context;

    public CloudAdvisor(ConsoleConfiguration configuration, ApplicationContext context) {
        this.configuration = configuration;
        this.context = context;
    }

    @Override
    public boolean isExecutionAllowed(Script script) {
        if (configuration.isEnabled()) {
            return true;
        }

        // functions has their own security checks
        if (context.getEnvironment().getActiveNames().contains(Environment.FUNCTION)) {
            return true;
        }

        // disable by default for the cloud environment (deployed apps)
        return !context.getEnvironment().getActiveNames().contains(Environment.CLOUD);
    }

    @Override
    public String toString() {
        return "Cloud advisor for environments " + String.join(", ", context.getEnvironment().getActiveNames()) + ", enabled = " + configuration.isEnabled();
    }

}

