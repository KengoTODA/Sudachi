/*
 * Copyright (c) 2021 Works Applications Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.worksap.nlp.sudachi.dictionary;

import java.nio.ShortBuffer;

public final class Connection implements Cloneable {
    private final ShortBuffer matrix;
    private final int leftSize;
    private final int rightSize;

    public Connection(ShortBuffer matrix, int leftSize, int rightSize) {
        this.matrix = matrix;
        this.leftSize = leftSize;
        this.rightSize = rightSize;
    }

    private int ix(int left, int right) {
        assert left < leftSize;
        assert right < rightSize;
        return right * leftSize + left;
    }

    public short cost(int left, int right) {
        return matrix.get(ix(left, right));
    }

    public int getLeftSize() {
        return leftSize;
    }

    public int getRightSize() {
        return rightSize;
    }

    public void setCost(int left, int right, short cost) {
        matrix.put(ix(left, right), cost);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Connection clone() {
        ShortBuffer copy = ShortBuffer.allocate(matrix.limit());
        copy.put(matrix);

        return new Connection(copy, leftSize, rightSize);
    }
}
