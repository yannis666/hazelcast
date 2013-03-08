/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.monitor.impl;

import com.hazelcast.monitor.LocalTopicOperationStats;
import com.hazelcast.monitor.LocalTopicStats;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;

public class LocalTopicStatsImpl extends LocalInstanceStatsSupport<LocalTopicOperationStats>
        implements LocalTopicStats {

    private long creationTime;
    private long totalPublishes;
    private long totalReceivedMessages;
    private long lastPublishTime;

    @Override
    LocalTopicOperationStats newOperationStatsInstance() {
        return new LocalTopicOperationStatsImpl();
    }

    @Override
    void writeDataInternal(ObjectDataOutput out) throws IOException {
        out.writeLong(creationTime);
        out.writeLong(totalPublishes);
        out.writeLong(totalReceivedMessages);
        out.writeLong(lastPublishTime);
    }

    @Override
    void readDataInternal(ObjectDataInput in) throws IOException {
        creationTime = in.readLong();
        totalPublishes = in.readLong();
        totalReceivedMessages = in.readLong();
        lastPublishTime = in.readLong();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getTotalPublishes() {
        return totalPublishes;
    }

    public void setTotalPublishes(long totalPublishes) {
        this.totalPublishes = totalPublishes;
    }

    public long getTotalReceivedMessages() {
        return totalReceivedMessages;
    }

    public void setTotalReceivedMessages(long totalReceivedMessages) {
        this.totalReceivedMessages = totalReceivedMessages;
    }

    public long getLastPublishTime() {
        return lastPublishTime;
    }

    public void setLastPublishTime(long lastPublishTime) {
        this.lastPublishTime = lastPublishTime;
    }

    @Override
    public String toString() {
        return "LocalTopicStatsImpl{" +
                "creationTime=" + creationTime +
                ", totalPublishes=" + totalPublishes +
                ", totalReceivedMessages=" + totalReceivedMessages +
                ", lastPublishTime=" + lastPublishTime +
                ", operationStats=" + getOperationStats() +
                '}';
    }
}