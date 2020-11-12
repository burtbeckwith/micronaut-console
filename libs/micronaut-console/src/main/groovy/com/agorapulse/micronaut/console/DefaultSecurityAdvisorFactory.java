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
package com.agorapulse.micronaut.console;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;

import javax.inject.Singleton;
import java.time.Instant;

@Factory
public class DefaultSecurityAdvisorFactory {

    @Bean
    @Singleton
    @Requires(property = "console.addresses")
    public SecurityAdvisor addressFilter(ConsoleConfiguration configuration) {
        return script -> {
            if (script.getUser() == null || script.getUser().getAddress() == null) {
                // address must be known
                return false;
            }
            return configuration.getAddresses().contains(script.getUser().getAddress());
        };
    }

    @Bean
    @Singleton
    @Requires(property = "console.users")
    public SecurityAdvisor userFilter(ConsoleConfiguration configuration) {
        return script -> {
            if (script.getUser() == null || script.getUser().getId() == null) {
                // id must be known
                return false;
            }
            return configuration.getUsers().contains(script.getUser().getId());
        };
    }

    @Bean
    @Singleton
    @Requires(property = "console.until")
    public SecurityAdvisor untilWindow(ConsoleConfiguration configuration) {
        return script -> Instant.now().isBefore(configuration.convertUntil());
    }

}
