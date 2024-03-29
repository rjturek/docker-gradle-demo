/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.plugins.docker.tasks.image

import org.gradle.api.plugins.docker.tasks.AbstractDockerTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

class DockerTagImage extends AbstractDockerTask {
    /**
     * Image ID to be tagged.
     */
    @Input
    String imageId

    /**
     * The repository to tag in.
     */
    @Input
    String repository

    /**
     * Image name to be tagged.
     */
    @Input
    String tag

    /**
     * Forces tagging.
     */
    @Input
    @Optional
    Boolean force

    @Override
    void runRemoteCommand(dockerClient) {
        logger.quiet "Tagging image with ID '${getImageId()}'."
        def tagImageCmd = dockerClient.tagImageCmd(getImageId(), getRepository(), getTag())

        if(getForce()) {
            tagImageCmd.withForce(getForce())
        }

        tagImageCmd.exec()
    }
}