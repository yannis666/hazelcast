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

package com.hazelcast.nio.serialization;


import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;

final class ByteArraySerializerAdapter implements SerializerAdapter {

    private final ByteArraySerializer serializer;

    public ByteArraySerializerAdapter(ByteArraySerializer serializer) {
        this.serializer = serializer;
    }

    @SuppressWarnings("unchecked")
    public void write(ObjectDataOutput out, Object object) throws IOException {
        final byte[] bytes = serializer.write(object);
        out.writeInt(bytes != null ? bytes.length : 0);
        out.write(bytes);
    }

    public Object read(ObjectDataInput in) throws IOException {
        final int len = in.readInt();
        if (len > 0) {
            final byte[] bytes = new byte[len];
            in.readFully(bytes);
            return serializer.read(bytes);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public byte[] write(Object object) throws IOException {
        return serializer.write(object);
    }

    public Object read(Data data) throws IOException {
        return serializer.read(data.buffer);
    }

    public int getTypeId() {
        return serializer.getTypeId();
    }

    public void destroy() {
        serializer.destroy();
    }

    @Override
    public Serializer getImpl() {
        return serializer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SerializerAdapter{");
        sb.append("serializer=").append(serializer);
        sb.append('}');
        return sb.toString();
    }
}
