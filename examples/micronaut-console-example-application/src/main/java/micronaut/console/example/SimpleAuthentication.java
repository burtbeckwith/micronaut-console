/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2022 Agorapulse.
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
package micronaut.console.example;

import io.micronaut.security.authentication.Authentication;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class SimpleAuthentication implements Authentication {

    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_ATTRIBUTES = "attributes";
    private final String name;

    public SimpleAuthentication(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }

    // @Override Micronaut 3 compatibility
    public Collection<String> getRoles() {
        return Collections.emptySet();
    }

}
