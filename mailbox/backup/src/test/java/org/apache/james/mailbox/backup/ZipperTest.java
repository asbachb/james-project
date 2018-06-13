/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package org.apache.james.mailbox.backup;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.james.junit.TemporaryFolderExtension;
import org.apache.james.junit.TemporaryFolderExtension.TemporaryFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.collect.ImmutableList;

@ExtendWith(TemporaryFolderExtension.class)
public class ZipperTest {
    private Zipper testee;

    @BeforeEach
    void beforeEach() {
        testee = new Zipper();
    }

    @Test
    void archiveShouldWriteEmptyValidArchiveWhenNoMessage(TemporaryFolder temporaryFolder) throws Exception {
        File destination = File.createTempFile("backup-test", ".zip", temporaryFolder.getTempDir());
        testee.archive(ImmutableList.of(), destination);

        try (ZipFile zipFile = new ZipFile(destination)) {
            assertThat(zipFile.getEntries().hasMoreElements()).isFalse();
        }
    }

}