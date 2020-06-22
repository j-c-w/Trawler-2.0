/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import bp.common.fp.Function;
import bp.common.fp.Function_2Args;
import bp.common.fp.Function_Int;
import bp.common.fp.Function_Int_2Args;
import bp.common.fp.Predicate_2Args;
import bp.common.fp.Predicate_3Args;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Array {
    public static Function transpose2DMatrix() {
        return new Function(){

            public Object fn(Object object) {
                int[][] arrn = (int[][])object;
                int[][] arrn2 = new int[arrn.length != 0 ? arrn[0].length : 0][arrn.length];
                for (int i = 0; i < arrn2.length; ++i) {
                    for (int j = 0; j < arrn2[i].length; ++j) {
                        arrn2[i][j] = arrn[j][i];
                    }
                }
                return arrn2;
            }
        };
    }

    public static Function reverseXAxisOf2DMatrix() {
        return new Function(){

            public Object fn(Object object) {
                int[][] arrn;
                int[][] arrn2 = new int[arrn.length][(arrn = (int[][])object).length != 0 ? arrn[0].length : 0];
                for (int i = 0; i < arrn2.length; ++i) {
                    for (int j = 0; j < arrn2[i].length; ++j) {
                        arrn2[i][j] = arrn[arrn.length - i - 1][j];
                    }
                }
                return arrn2;
            }
        };
    }

    public static Function reverseYAxisOf2DMatrix() {
        return new Function(){

            public Object fn(Object object) {
                int[][] arrn;
                int[][] arrn2 = new int[arrn.length][(arrn = (int[][])object).length != 0 ? arrn[0].length : 0];
                for (int i = 0; i < arrn2.length; ++i) {
                    for (int j = 0; j < arrn2[i].length; ++j) {
                        arrn2[i][j] = arrn[i][arrn[i].length - 1 - j];
                    }
                }
                return arrn2;
            }
        };
    }

    public static void reverseArray(int[] arrn, int n) {
        int n2 = n - 1;
        int n3 = 0;
        while (n3 < n / 2) {
            int n4 = arrn[n3];
            arrn[n3] = arrn[n2];
            arrn[n2] = n4;
            ++n3;
            --n2;
        }
    }

    public static void reverseArray(float[] arrf, int n, int n2) {
        int n3 = n2 - 1;
        int n4 = (n2 - n) / 2 + n;
        int n5 = n;
        while (n5 < n4) {
            float f = arrf[n5];
            arrf[n5] = arrf[n3];
            arrf[n3] = f;
            ++n5;
            --n3;
        }
    }

    public static void reverseArray(double[] arrd, int n) {
        int n2 = n - 1;
        int n3 = 0;
        while (n3 < n / 2) {
            double d = arrd[n3];
            arrd[n3] = arrd[n2];
            arrd[n2] = d;
            ++n3;
            --n2;
        }
    }

    public static void mingle(int[] arrn, int n) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < n; i += 2) {
            int n2 = arrn[i];
            arrn[i] = arrn[i + 1];
            arrn[i + 1] = n2;
        }
    }

    public static final int binarySearchIndex_TwoStep(int[] arrn, int n) {
        for (int i = 0; i < arrn.length; i += 2) {
            if (arrn[i] < n) continue;
            return i;
        }
        return arrn.length;
    }

    public static final int binarySearchIndex_TwoStep(int[] arrn, int n, int n2, int n3) {
        int n4;
        if (n > arrn[n2 + n3 - 2]) {
            return n2 + n3;
        }
        int n5 = n4 = n3 / 4;
        int n6 = arrn[(n5 << 1) + n2];
        while (n4 != 0) {
            if (n6 < n) {
                n5 += n4 & 1;
                n6 = arrn[((n5 += (n4 /= 2)) << 1) + n2];
                continue;
            }
            if (n6 > n) {
                n5 -= n4 & 1;
                n6 = arrn[((n5 -= (n4 /= 2)) << 1) + n2];
                continue;
            }
            return (n5 << 1) + n2;
        }
        return ((n6 >= n ? n5 : n5 + 1) << 1) + n2;
    }

    public static Function_Int_2Args matrix2DLookup(final int[] arrn, final int n) {
        return new Function_Int_2Args(){

            public int fn(int n3, int n2) {
                return arrn[n2 * n + n3];
            }
        };
    }

    public static Function_Int matrix1DLookUp(final int[] arrn) {
        return new Function_Int(){

            public int fn(int n) {
                return arrn[n];
            }
        };
    }

    public static Function_Int binarySearch(final int[] arrn) {
        return new Function_Int(){

            public int fn(int n) {
                return Array.binarySearch(n, arrn);
            }
        };
    }

    public static final int binarySearch(int n, int[] arrn) {
        int n2 = Arrays.binarySearch(arrn, n);
        return n2 < 0 ? -(n2 + 1) : n2;
    }

    public static Predicate_3Args arraysEqual() {
        return new Predicate_3Args(){

            public boolean test(Object object, Object object2, Object object3) {
                if (object instanceof Object[]) {
                    if (object2 instanceof Object[]) {
                        if (((Object[])object).length != ((Object[])object2).length) {
                            return false;
                        }
                        for (int i = 0; i < ((Object[])object).length; ++i) {
                            if (this.test(((Object[])object)[i], ((Object[])object2)[i], object3)) continue;
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
                return ((Predicate_2Args)object3).test(object, object2);
            }
        };
    }

    public static double[] sum(double[] arrd, double[] arrd2, Function_2Args function_2Args) {
        double[] arrd3 = (double[])arrd.clone();
        if (arrd.length != arrd2.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < arrd3.length; ++i) {
            arrd3[i] = ((Number)function_2Args.fn(new Double(arrd3[i]), new Double(arrd2[i]))).doubleValue();
        }
        return arrd3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int intersection(int[] var0, int var1_1, int[] var2_2, int var3_3, int[] var4_4) {
        var5_5 = 0;
        var6_6 = 0;
        var7_7 = 0;
        if (var1_1 == 0) return 0;
        if (var3_3 == 0) {
            return 0;
        }
        do lbl-1000:
        // 4 sources
        {
            block3: {
                block2: {
                    if (var0[var5_5] >= var2_2[var6_6]) break block2;
                    if (++var5_5 < var1_1) ** GOTO lbl-1000
                    return var7_7;
                }
                if (var0[var5_5] <= var2_2[var6_6]) break block3;
                if (++var6_6 < var3_3) ** GOTO lbl-1000
                return var7_7;
            }
            var4_4[var7_7++] = var0[var5_5];
            if (++var5_5 >= var1_1) return var7_7;
        } while (++var6_6 < var3_3);
        return var7_7;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int merge_TwoStep(int[] var0, int var1_1, int[] var2_2, int var3_3, Function_Int_2Args var4_4, int[] var5_5) {
        block1: {
            var6_6 = 0;
            var7_7 = 0;
            var8_8 = 0;
            if (var6_6 >= var1_1 || var7_7 >= var3_3) break block1;
            do lbl-1000:
            // 4 sources
            {
                block3: {
                    block2: {
                        if (var0[var6_6] >= var2_2[var7_7]) break block2;
                        var5_5[var8_8] = var0[var6_6];
                        var5_5[var8_8 + 1] = var0[var6_6 + 1];
                        var8_8 += 2;
                        if ((var6_6 += 2) < var1_1) ** GOTO lbl-1000
                        break;
                    }
                    if (var0[var6_6] <= var2_2[var7_7]) break block3;
                    var5_5[var8_8] = var2_2[var7_7];
                    var5_5[var8_8 + 1] = var2_2[var7_7 + 1];
                    var8_8 += 2;
                    if ((var7_7 += 2) < var3_3) ** GOTO lbl-1000
                    break;
                }
                var5_5[var8_8] = var0[var6_6];
                var5_5[var8_8 + 1] = var4_4.fn(var0[var6_6 + 1], var2_2[var7_7 + 1]);
                var8_8 += 2;
            } while ((var6_6 += 2) < var1_1 && (var7_7 += 2) < var3_3);
        }
        System.arraycopy(var0, var6_6, var5_5, var8_8, var1_1 - var6_6);
        System.arraycopy(var2_2, var7_7, var5_5, var8_8 += var1_1 - var6_6, var3_3 - var7_7);
        return var8_8 += var3_3 - var7_7;
    }

    public static final int getMin(int[] arrn) {
        if (arrn.length > 0) {
            int n = arrn[0];
            for (int i = 1; i < arrn.length; ++i) {
                if (arrn[i] >= n) continue;
                n = arrn[i];
            }
            return n;
        }
        return Integer.MAX_VALUE;
    }

    public static int getMax(int[] arrn) {
        if (arrn.length > 0) {
            int n = arrn[0];
            for (int i = 1; i < arrn.length; ++i) {
                if (arrn[i] <= n) continue;
                n = arrn[i];
            }
            return n;
        }
        return Integer.MIN_VALUE;
    }

    public static final void fill(float[] arrf, float[] arrf2, int n) {
        int n2 = 0;
        while (n-- > 0) {
            System.arraycopy(arrf, 0, arrf2, n2, arrf.length);
            n2 += arrf.length;
        }
    }

    public static final int uniq(int[] arrn, int[] arrn2) {
        if (arrn.length > 0) {
            int n;
            int n2 = 1;
            arrn2[0] = n = arrn[0];
            for (int i = 1; i < arrn.length; ++i) {
                int n3 = arrn[i];
                if (n3 == n) continue;
                n = n3;
                arrn2[n2++] = n3;
            }
            return n2;
        }
        return 0;
    }

    public static final int indexOf(Object[] arrobject, Object object) {
        for (int i = 0; i < arrobject.length; ++i) {
            if (!arrobject[i].equals(object)) continue;
            return i;
        }
        return Integer.MAX_VALUE;
    }

    public static final int[] convertToInts(String[] arrstring) {
        int[] arrn = new int[arrstring.length];
        for (int i = 0; i < arrstring.length; ++i) {
            arrn[i] = Integer.parseInt(arrstring[i]);
        }
        return arrn;
    }

    public static final boolean[] convertToBooleans(String[] arrstring) {
        boolean[] arrbl = new boolean[arrstring.length];
        Pattern pattern = Pattern.compile("[tT][rR][uU][eE]");
        for (int i = 0; i < arrstring.length; ++i) {
            arrbl[i] = pattern.matcher(arrstring[i]).matches();
        }
        return arrbl;
    }

    public static int[] concatenate(int[] arrn, int[] arrn2) {
        int[] arrn3 = new int[arrn.length + arrn2.length];
        System.arraycopy(arrn, 0, arrn3, 0, arrn.length);
        System.arraycopy(arrn2, 0, arrn3, arrn.length, arrn2.length);
        return arrn3;
    }

    public static boolean[] concatenate(boolean[] arrbl, boolean[] arrbl2) {
        boolean[] arrbl3 = new boolean[arrbl.length + arrbl2.length];
        System.arraycopy(arrbl, 0, arrbl3, 0, arrbl.length);
        System.arraycopy(arrbl2, 0, arrbl3, arrbl.length, arrbl2.length);
        return arrbl3;
    }

    public static int[] indicesOf(int[] arrn, int n, int n2) {
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            if (arrn[i] != n2) continue;
            ++n3;
        }
        int[] arrn2 = new int[n3];
        n3 = 0;
        for (int i = 0; i < n; ++i) {
            if (arrn[i] != n2) continue;
            arrn2[n3++] = i;
        }
        return arrn2;
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class FloatStack {
    static final Logger logger = Logger.getLogger(FloatStack.class.getName());
    List<float[]> chunks = new ArrayList<float[]>(10);
    float[] iA;
    int i;
    int j;
    int chunkSize;

    public FloatStack(int n) {
        if (n == 0) {
            throw new IllegalArgumentException();
        }
        this.iA = new float[n];
        this.chunks.add(this.iA);
        this.chunkSize = n;
        this.i = 0;
        this.j = 0;
    }

    public final void stuff(float f) {
        if (this.i < this.iA.length) {
            this.iA[this.i++] = f;
        } else {
            this.i = 1;
            if (++this.j < this.chunks.size()) {
                this.iA = this.chunks.get(this.j);
            } else {
                this.iA = new float[this.chunkSize];
                logger.info(" Adding new float chunk " + this.chunkSize);
                this.chunks.add(this.iA);
            }
            this.iA[0] = f;
        }
    }

    public final float unstuff() {
        if (this.i > 0) {
            return this.iA[--this.i];
        }
        if (this.j > 0) {
            --this.j;
            this.iA = this.chunks.get(this.j);
            this.i = this.iA.length - 1;
            return this.iA[this.i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final void reset() {
        this.i = 0;
        this.j = 0;
        this.iA = this.chunks.get(0);
    }

    public final boolean empty() {
        return this.i == 0 && this.j == 0;
    }

    public final int getMark() {
        return this.j * this.chunkSize + this.i;
    }

    public final void reset(int n) {
        this.i = n % this.chunkSize;
        this.j = n / this.chunkSize;
        this.iA = this.chunks.get(this.j);
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import bp.common.fp.Function_Int_2Args;
import java.util.Arrays;

public final class IntCrapHash {
    int[] hash;
    int[] entries;
    int[] bP;
    int eI = 0;
    int mask = 1;

    public IntCrapHash(int n) {
        while ((--n & this.mask) < n) {
            this.mask = this.mask * 2 + 1;
        }
        this.hash = new int[this.mask + 1];
        Arrays.fill(this.hash, Integer.MAX_VALUE);
        this.entries = new int[this.hash.length * 2];
        this.bP = new int[this.hash.length];
    }

    private final int getHash(int n) {
        return n & this.mask;
    }

    public final void put(int n, int n2, Function_Int_2Args function_Int_2Args) {
        if (this.eI >= this.entries.length) {
            this.resize();
        }
        int n3 = this.getHash(n);
        int n4 = this.hash[n3];
        do {
            if (n4 == Integer.MAX_VALUE) {
                this.hash[n3] = this.eI;
                this.entries[this.eI] = n;
                this.entries[this.eI + 1] = n2;
                this.bP[this.eI / 2] = n3;
                this.eI += 2;
                return;
            }
            if (this.entries[n4] == n) {
                this.entries[n4 + 1] = function_Int_2Args.fn(this.entries[n4 + 1], n2);
                return;
            }
            n3 = n3 + 1 < this.hash.length ? n3 + 1 : 0;
            n4 = this.hash[n3];
        } while (true);
    }

    public final int size() {
        return this.eI / 2;
    }

    public final int getEntries(int[] arrn) {
        System.arraycopy(this.entries, 0, arrn, 0, this.eI);
        return this.eI;
    }

    public final void clear() {
        int n = this.eI / 2;
        for (int i = 0; i < n; ++i) {
            this.hash[this.bP[i]] = Integer.MAX_VALUE;
        }
        this.eI = 0;
    }

    private final void resize() {
        IntCrapHash intCrapHash = new IntCrapHash(this.hash.length * 2 + 1);
        for (int i = 0; i < this.eI; i += 2) {
            intCrapHash.put(this.entries[i], this.entries[i + 1], null);
        }
        this.hash = intCrapHash.hash;
        this.entries = intCrapHash.entries;
        this.bP = intCrapHash.bP;
        this.eI = intCrapHash.eI;
        this.mask = intCrapHash.mask;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import bp.common.fp.Generator_Int;
import bp.common.fp.Procedure_Int;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class IntStack
implements Procedure_Int,
Generator_Int {
    static final Logger logger = Logger.getLogger(IntStack.class.getName());
    List<int[]> chunks = new ArrayList<int[]>(10);
    int[] iA;
    int i;
    int j;
    int chunkSize;

    public IntStack(int n) {
        if (n == 0) {
            throw new IllegalArgumentException();
        }
        this.iA = new int[n];
        this.chunks.add(this.iA);
        this.chunkSize = n;
        this.i = 0;
        this.j = 0;
    }

    public final void stuff(int n) {
        if (this.i < this.iA.length) {
            this.iA[this.i++] = n;
        } else {
            this.i = 1;
            if (++this.j < this.chunks.size()) {
                this.iA = this.chunks.get(this.j);
            } else {
                this.iA = new int[this.chunkSize];
                logger.info(" Adding new chunk " + this.chunkSize);
                this.chunks.add(this.iA);
            }
            this.iA[0] = n;
        }
    }

    public final int unstuff() {
        if (this.i > 0) {
            return this.iA[--this.i];
        }
        if (this.j > 0) {
            --this.j;
            this.iA = this.chunks.get(this.j);
            this.i = this.iA.length - 1;
            return this.iA[this.i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final void reset() {
        this.i = 0;
        this.j = 0;
        this.iA = this.chunks.get(0);
    }

    public final boolean empty() {
        return this.i == 0 && this.j == 0;
    }

    public final void pro(int n) {
        this.stuff(n);
    }

    public final int gen() {
        return !this.empty() ? this.unstuff() : Integer.MAX_VALUE;
    }

    public final int getMark() {
        return this.j * this.chunkSize + this.i;
    }

    public final void reset(int n) {
        this.i = n % this.chunkSize;
        this.j = n / this.chunkSize;
        this.iA = this.chunks.get(this.j);
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

public class LockedObject {
    private final Object o;
    private boolean locked;

    public LockedObject(Object object) {
        this.o = object;
        this.locked = false;
    }

    public final Object get() {
        if (this.locked) {
            throw new IllegalStateException();
        }
        this.locked = true;
        return this.o;
    }

    public final void release() {
        if (!this.locked) {
            throw new IllegalStateException();
        }
        this.locked = false;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import java.util.logging.Logger;

public class ScrollingQueue {
    static final Logger logger = Logger.getLogger(ScrollingQueue.class.getName());
    private Object[] iA;
    private int firstIndex;
    private int lastIndex;
    private int mask = 1;

    public ScrollingQueue(int n, int n2) {
        while ((--n & this.mask) < n) {
            this.mask = this.mask * 2 + 1;
        }
        this.iA = new Object[this.mask + 1];
        this.lastIndex = this.firstIndex = n2;
    }

    public final void add(Object object) {
        if (this.size() < this.iA.length) {
            this.iA[this.lastIndex++ & this.mask] = object;
        } else {
            logger.info(" Increasing size of ScrollingQueue array from " + this.iA.length + " for " + this);
            int n = this.mask * 2 + 1;
            Object[] arrobject = new Object[n + 1];
            int n2 = this.firstIndex & this.mask;
            int n3 = this.firstIndex & n;
            if (this.iA.length - n2 > arrobject.length - n3) {
                int n4 = arrobject.length - n3;
                System.arraycopy(this.iA, n2, arrobject, n3, n4);
                n3 = this.iA.length - (n2 + n4);
                System.arraycopy(this.iA, n2 + n4, arrobject, 0, n3);
                System.arraycopy(this.iA, 0, arrobject, n3, n2);
            } else {
                System.arraycopy(this.iA, n2, arrobject, n3, this.iA.length - n2);
                if ((n3 += this.iA.length - n2) + n2 < arrobject.length) {
                    System.arraycopy(this.iA, 0, arrobject, n3, n2);
                } else {
                    System.arraycopy(this.iA, 0, arrobject, n3, arrobject.length - n3);
                    System.arraycopy(this.iA, arrobject.length - n3, arrobject, 0, n2 - (arrobject.length - n3));
                }
            }
            this.iA = arrobject;
            this.mask = n;
            this.add(object);
        }
    }

    public final Object get(int n) {
        if (n >= this.lastIndex || n < this.firstIndex) {
            throw new ArrayIndexOutOfBoundsException(n + " " + this.lastIndex + " " + this.firstIndex);
        }
        return this.iA[n & this.mask];
    }

    public final Object removeFirst() {
        if (this.firstIndex == this.lastIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n = this.firstIndex & this.mask;
        Object object = this.iA[n];
        this.iA[n] = null;
        ++this.firstIndex;
        return object;
    }

    public final void removeUpto(int n) {
        if (n > this.lastIndex || n < this.firstIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (this.firstIndex < n) {
            this.iA[this.firstIndex & this.mask] = null;
            ++this.firstIndex;
        }
    }

    public final void set(int n, Object object) {
        if (n >= this.lastIndex || n < this.firstIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.iA[n & this.mask] = object;
    }

    public final int size() {
        return this.lastIndex - this.firstIndex;
    }

    public final int firstIndex() {
        return this.firstIndex;
    }

    public final int lastIndex() {
        return this.lastIndex;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import bp.common.fp.Function_Int;
import java.util.logging.Logger;

public final class ScrollingQueue_Int
implements Function_Int {
    static final Logger logger = Logger.getLogger(ScrollingQueue_Int.class.getName());
    private int[] iA;
    private int firstIndex;
    private int lastIndex;
    private int mask = 1;
    private final int initialSize;
    private static final int DECREASE_MULTIPLE = 4;

    public ScrollingQueue_Int(int n, int n2, boolean bl) {
        while ((--n & this.mask) < n) {
            this.mask = this.mask * 2 + 1;
        }
        this.iA = new int[this.mask + 1];
        this.lastIndex = this.firstIndex = n2;
        this.initialSize = this.iA.length;
    }

    public final void add(int n) {
        if (this.size() < this.iA.length) {
            this.iA[this.lastIndex++ & this.mask] = n;
        } else {
            logger.info(" Increasing size of ScrollingQueue_Int array from " + this.iA.length + " for " + this);
            int n2 = this.mask * 2 + 1;
            int[] arrn = new int[n2 + 1];
            this.copyToNewArray(arrn, n2);
            this.add(n);
        }
    }

    final void copyToNewArray(int[] arrn, int n) {
        int n2 = this.firstIndex & this.mask;
        int n3 = this.size();
        if (n2 + n3 <= this.iA.length) {
            this.copyChunkToNewArray(arrn, n, this.firstIndex, n3);
        } else {
            int n4 = this.iA.length - n2;
            this.copyChunkToNewArray(arrn, n, this.firstIndex, n4);
            this.copyChunkToNewArray(arrn, n, this.firstIndex + n4, n3 - n4);
        }
        this.iA = arrn;
        this.mask = n;
    }

    final void copyChunkToNewArray(int[] arrn, int n, int n2, int n3) {
        int n4 = n2 & this.mask;
        int n5 = n2 & n;
        if (n5 + n3 > arrn.length) {
            int n6 = arrn.length - n5;
            System.arraycopy(this.iA, n4, arrn, n5, n6);
            System.arraycopy(this.iA, n4 + n6, arrn, 0, n3 - n6);
        } else {
            System.arraycopy(this.iA, n4, arrn, n5, n3);
        }
    }

    public final int fn(int n) {
        if (n >= this.lastIndex || n < this.firstIndex) {
            throw new ArrayIndexOutOfBoundsException(n + " " + this.lastIndex() + " " + this.firstIndex());
        }
        return this.iA[n & this.mask];
    }

    public final int get(int n) {
        if (n >= this.lastIndex || n < this.firstIndex) {
            throw new ArrayIndexOutOfBoundsException(n + " " + this.lastIndex() + " " + this.firstIndex());
        }
        return this.iA[n & this.mask];
    }

    public final void removeFirst() {
        if (this.firstIndex == this.lastIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ++this.firstIndex;
        if (this.iA.length > this.initialSize && this.iA.length > this.size() * 4) {
            logger.info(" Decreasing size of ScrollingQueue_Int array from " + this.iA.length + " for " + this);
            int n = (this.mask - 1) / 2;
            int[] arrn = new int[n + 1];
            this.copyToNewArray(arrn, n);
            this.iA = arrn;
        }
    }

    public final void set(int n, int n2) {
        if (n >= this.lastIndex || n < this.firstIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.iA[n & this.mask] = n2;
    }

    public final int size() {
        return this.lastIndex - this.firstIndex;
    }

    public final int firstIndex() {
        return this.firstIndex;
    }

    public final int lastIndex() {
        return this.lastIndex;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import bp.common.ds.ScrollingQueue_Int;
import bp.common.fp.Generator_Int;
import bp.common.fp.Procedure_Int;
import bp.common.fp.Procedure_NoArgs;

public final class ScrollingQueue_IntTools {
    public static final Procedure_Int fillFromGenerator(final Generator_Int generator_Int, final ScrollingQueue_Int scrollingQueue_Int) {
        return new Procedure_Int(){

            public final void pro(int n) {
                while (scrollingQueue_Int.lastIndex() < n) {
                    scrollingQueue_Int.add(generator_Int.gen());
                }
            }
        };
    }

    public static final Procedure_Int add(final ScrollingQueue_Int scrollingQueue_Int) {
        return new Procedure_Int(){

            public final void pro(int n) {
                scrollingQueue_Int.add(n);
            }
        };
    }

    public static final Procedure_NoArgs removeFirst(final ScrollingQueue_Int scrollingQueue_Int) {
        return new Procedure_NoArgs(){

            public final void pro() {
                scrollingQueue_Int.removeFirst();
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import java.io.PrintStream;
import java.util.Random;

public final class SkipList {
    int myMaxLevel;
    int myLevel;
    SkipListNode myHeader;
    SkipListNode nilNode;
    float myProbability;
    public static final int NIL_KEY = Integer.MAX_VALUE;
    static final float GOOD_PROB = 0.25f;
    public static final Object NOT_FOUND_OBJ = null;

    public SkipList(float f, int n) {
        this.myProbability = f;
        this.myMaxLevel = n;
        this.myLevel = 0;
        this.myHeader = new SkipListNode(this.myMaxLevel, Integer.MIN_VALUE, null);
        this.nilNode = new SkipListNode(this.myMaxLevel, Integer.MAX_VALUE, null);
        for (int i = 0; i <= this.myMaxLevel; ++i) {
            this.myHeader.forward[i] = this.nilNode;
        }
    }

    public SkipList() {
        this(Integer.MAX_VALUE);
    }

    public SkipList(long l) {
        this(0.25f, (int)Math.ceil(Math.log(l) / Math.log(4.0)) - 1);
    }

    public void clear() {
        for (int i = 0; i <= this.myMaxLevel; ++i) {
            this.myHeader.forward[i] = this.nilNode;
        }
        this.myLevel = 0;
    }

    public void delete(int n) {
        int n2;
        SkipListNode[] arrskipListNode = new SkipListNode[this.myMaxLevel + 1];
        SkipListNode skipListNode = this.myHeader;
        for (n2 = this.myLevel; n2 >= 0; --n2) {
            while (skipListNode.forward[n2].key < n) {
                skipListNode = skipListNode.forward[n2];
            }
            arrskipListNode[n2] = skipListNode;
        }
        skipListNode = skipListNode.forward[0];
        if (skipListNode.key == n) {
            for (n2 = 0; n2 <= this.myLevel; ++n2) {
                if (arrskipListNode[n2].forward[n2] != skipListNode) continue;
                arrskipListNode[n2].forward[n2] = skipListNode.forward[n2];
            }
            while (this.myLevel > 0 && this.myHeader.forward[this.myLevel].key == Integer.MAX_VALUE) {
                --this.myLevel;
            }
        }
    }

    protected int generateRandomLevel() {
        int n;
        for (n = 0; n < this.myMaxLevel && Math.random() < (double)this.myProbability; ++n) {
        }
        return n;
    }

    public void insert(int n, Object object) {
        int n2;
        SkipListNode[] arrskipListNode = new SkipListNode[this.myMaxLevel + 1];
        SkipListNode skipListNode = this.myHeader;
        for (n2 = this.myLevel; n2 >= 0; --n2) {
            while (skipListNode.forward[n2].key < n) {
                skipListNode = skipListNode.forward[n2];
            }
            arrskipListNode[n2] = skipListNode;
        }
        skipListNode = skipListNode.forward[0];
        if (skipListNode.key == n) {
            skipListNode.value = object;
        } else {
            int n3;
            n2 = this.generateRandomLevel();
            if (n2 > this.myLevel) {
                for (n3 = this.myLevel + 1; n3 <= n2; ++n3) {
                    arrskipListNode[n3] = this.myHeader;
                }
                this.myLevel = n2;
            }
            skipListNode = new SkipListNode(n2, n, object);
            for (n3 = 0; n3 <= n2; n3 = (int)((short)(n3 + 1))) {
                skipListNode.forward[n3] = arrskipListNode[n3].forward[n3];
                arrskipListNode[n3].forward[n3] = skipListNode;
            }
        }
    }

    public Iterator iterator() {
        return new Iterator();
    }

    private SkipListNode rangeEnd(int n) {
        SkipListNode skipListNode = this.myHeader;
        for (int i = this.myLevel; i >= 0; --i) {
            SkipListNode skipListNode2 = skipListNode.forward[i];
            while (skipListNode2.key < n) {
                skipListNode = skipListNode2;
                skipListNode2 = skipListNode.forward[i];
            }
        }
        return skipListNode;
    }

    private SkipListNode rangeStart(int n) {
        SkipListNode skipListNode = this.myHeader;
        for (int i = this.myLevel; i >= 0; --i) {
            SkipListNode skipListNode2 = skipListNode.forward[i];
            while (skipListNode2.key < n) {
                skipListNode = skipListNode2;
                skipListNode2 = skipListNode.forward[i];
            }
        }
        skipListNode = skipListNode.forward[0];
        return skipListNode;
    }

    public Object search(int n) {
        SkipListNode skipListNode = this.myHeader;
        for (int i = this.myLevel; i >= 0; --i) {
            SkipListNode skipListNode2 = skipListNode.forward[i];
            while (skipListNode2.key < n) {
                skipListNode = skipListNode2;
                skipListNode2 = skipListNode.forward[i];
            }
        }
        skipListNode = skipListNode.forward[0];
        if (skipListNode.key == n) {
            return skipListNode.value;
        }
        return NOT_FOUND_OBJ;
    }

    public Object searchGreaterThanOrEqual(int n) {
        SkipListNode skipListNode = this.rangeStart(n);
        return skipListNode.value;
    }

    public final Object searchLessThan(int n) {
        SkipListNode skipListNode = this.rangeEnd(n);
        return skipListNode.value;
    }

    public static void main(String[] arrstring) {
        int n;
        String[] arrstring2 = new String[]{" booo ", " ship ", " jonah ", " etc "};
        SkipList skipList = new SkipList(100000L);
        Random random = new Random();
        int[] arrn = new int[Integer.parseInt(arrstring[0])];
        for (n = 0; n < Integer.parseInt(arrstring[0]); ++n) {
            int n2;
            arrn[n] = n2 = random.nextInt(Integer.MAX_VALUE);
            skipList.insert(n2, n2 + " " + arrstring2[random.nextInt(arrstring2.length)]);
        }
        System.out.println(skipList.toString());
        for (n = 0; n < Integer.parseInt(arrstring[0]); ++n) {
            System.out.println(skipList.search(arrn[n]).toString());
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        SkipListNode skipListNode = this.myHeader;
        while (skipListNode.key != Integer.MAX_VALUE) {
            if (skipListNode.value != null) {
                stringBuffer.append(skipListNode.value.toString() + "\n");
            }
            skipListNode = skipListNode.forward[0];
        }
        return stringBuffer.toString();
    }
    public static class SkipListNode {
        int key;
        Object value;
        SkipListNode[] forward;

        public SkipListNode(int n, int n2, Object object) {
            this.key = n2;
            this.value = object;
            this.forward = new SkipListNode[n + 1];
        }
    }
    public class Iterator
    implements java.util.Iterator {
        SkipListNode cursor;
        int stop;
        int cValue = Integer.MIN_VALUE;

        Iterator() {
            this.cursor = SkipList.this.myHeader.forward[0];
            this.stop = Integer.MAX_VALUE;
        }

        public int currentNodeKey() {
            return this.cValue;
        }

        public boolean hasNext() {
            return this.cursor.key < this.stop;
        }

        public Object next() {
            SkipListNode skipListNode = this.cursor;
            this.cValue = skipListNode.key;
            this.cursor = this.cursor.forward[0];
            return skipListNode.value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds;

import bp.common.io.Debug;

public final class SuffixTree {
    public final byte TERMINALCHAR;
    public static final int NOTANODE = 536870912;
    public static final int DEPTHISLOW = Integer.MIN_VALUE;
    public static final int DISTANCE = -134217728;
    public static final int IDISTANCE = 134217727;
    public static final int REFERENCE = 536870911;
    public static final int SIBLINGORSUFFIXLINK = 1073741823;
    public static final int ISIBLINGORSUFFIXLINK = -1073741824;
    public static final int FIRSTCHILD1 = 134217727;
    public static final int IFIRSTCHILD1 = -134217728;
    public static final int FIRSTCHILD2 = -1073741824;
    public static final int IFIRSTCHILD2 = 1073741823;
    public static final int DEPTHLOW = 1023;
    public static final int DEPTHHIGH = 134217727;
    public static final int SUFFIXLINKLOW1 = 2147482624;
    public static final int SUFFIXLINKLOW2 = -134217728;
    public static final int SUFFIXLINKLOW3 = -1073741824;
    public static final int HEADPOSITION = 134217727;
    public byte[] string;
    public int[] leaves;
    public int[] branches;
    public int memoryUsage;
    public int internalNodeCount = 1;

    public SuffixTree(byte[] arrby, double d, boolean bl, byte by) {
        this.TERMINALCHAR = by;
        this.string = arrby;
        this.leaves = new int[arrby.length];
        this.branches = new int[(int)((double)arrby.length * (d - 5.0) / 4.0) + 100];
        this.looseMcCreightsAlgorithm();
        if (bl) {
            this.leaves = new int[arrby.length];
            this.branches = new int[this.memoryUsage + 64];
            this.looseMcCreightsAlgorithm();
        }
    }

    private void looseMcCreightsAlgorithm() {
        int n = 2;
        int n2 = 1;
        int n3 = 1;
        int n4 = -1;
        NE nE = new NE(0, 0);
        this.constructLeafNode(this.makeLeaf(0));
        this.constructSmallNode(0, 0, 0, 0);
        this.setFirstChild(0, this.makeLeaf(0));
        while (n3 < this.string.length) {
            int n5;
            if (nE.e > 0) {
                if (n2 > 31) {
                    this.constructLargeNode(n - 2, n4 + 1 + nE.e, n3 - 1, n += 2, n2);
                    n2 = 0;
                }
                this.constructSmallNode(n, n2, n4 + nE.e, n3);
                ++this.internalNodeCount;
                n5 = this.getChild(nE.n, this.string[n3 + n4]);
                this.setNthChild(nE.n, n);
                this.setFirstChild(n, n5);
                this.constructLeafNode(this.makeLeaf(n3));
                this.setNthChild(n, this.makeLeaf(n3));
                n += 2;
                ++n2;
            } else {
                if (n2 > 0) {
                    this.constructLargeNode(n - 2, n4 + 1, n3 - 1, nE.n, n2);
                    n2 = 0;
                    n += 2;
                }
                nE = this.scanPrefix(nE, n3, this.string.length, this.string);
                n4 = this.depth(nE.n);
                if (nE.e > 0) continue;
                this.constructLeafNode(this.makeLeaf(n3));
                this.setNthChild(nE.n, this.makeLeaf(n3));
            }
            ++n3;
            nE.n = this.suffixLink(nE.n);
            n5 = nE.e;
            nE.e = 0;
            if (n5 > 0) {
                nE = this.rescan(nE, n3, n3 + n4 - 1 + n5, this.string);
            }
            n4 = this.depth(nE.n);
        }
        this.memoryUsage = n;
    }

    private final int compare(byte by, byte by2) {
        return by == by2 ? 0 : (by == this.TERMINALCHAR ? 1 : (by2 == this.TERMINALCHAR ? -1 : (by > by2 ? 1 : -1)));
    }

    private void constructLeafNode(int n) {
        this.leaves[n / 2] = 536870912;
    }

    private void constructSmallNode(int n, int n2, int n3, int n4) {
        int n5 = n3 + n2 < 32 ? n3 + n2 : 31;
        this.branches[n] = n5 - n2 << 27;
        this.branches[n + 1] = 536870912;
        if (n2 == 0) {
            this.branches[n + 2 * n5 + 2] = n3 < 32 ? 0 : n3 - 31;
            this.branches[n + 2 * n5 + 3] = n4 + n5;
        }
    }

    private void constructLargeNode(int n, int n2, int n3, int n4, int n5) {
        if (n2 > 1023) {
            this.branches[n + 2] = n2;
            this.branches[n + 3] = n3;
            int n6 = this.firstChild(n);
            int n7 = this.siblingOrSuffixLink(n6);
            while (this.isNode(n7)) {
                n6 = n7;
                n7 = this.siblingOrSuffixLink(n7);
            }
            this.setSiblingOrSuffixLink(n6, n4 | 0x20000000);
        } else {
            this.branches[n + 2] = n2 | (n4 /= 2) << 10 & 0x7FFFFC00 | Integer.MIN_VALUE;
            this.branches[n + 3] = n3 | n4 << 6 & 0xF8000000;
            int n8 = n3;
            this.leaves[n8] = this.leaves[n8] | n4 << 4 & 0xC0000000;
        }
        while (n5-- > 0) {
            this.setDistance(n - 2 * n5, n5);
        }
    }

    public final int depth(int n) {
        int n2 = this.distance(n);
        int n3 = this.branches[n + n2 * 2 + 2];
        return ((n3 & Integer.MIN_VALUE) != 0 ? n3 & 0x3FF : n3 & 0x7FFFFFF) + n2;
    }

    private final int distance(int n) {
        return this.branches[n] >>> 27;
    }

    public final int firstChild(int n) {
        return this.branches[n] & 0x7FFFFFF | (this.branches[n + 1] & 0xC0000000) >>> 3;
    }

    public final byte getByte(int n, int n2) {
        return this.string[(this.isLeaf(n) ? n / 2 : this.headPosition(n)) + n2];
    }

    public final int getChild(int n, byte by) {
        int n2 = this.firstChild(n);
        int n3 = this.depth(n);
        while (this.isNode(n2) && this.compare(this.getByte(n2, n3), by) < 0) {
            n2 = this.siblingOrSuffixLink(n2);
        }
        return this.isNode(n2) && this.compare(by, this.getByte(n2, n3)) == 0 ? n2 : 536870912;
    }

    public final int getChild(int n, int n2, byte by) {
        int n3 = this.firstChild(n);
        while (this.isNode(n3)) {
            byte by2 = this.getByte(n3, n2);
            if (by2 >= by) {
                return by2 == by ? n3 : 536870912;
            }
            n3 = this.siblingOrSuffixLink(n3);
        }
        return 536870912;
    }

    public int getSuffixLink(int n) {
        while (this.isNode(n)) {
            n = this.siblingOrSuffixLink(n);
        }
        return n & 0x1FFFFFFF;
    }

    public final int headPosition(int n) {
        int n2 = this.distance(n);
        return (this.branches[n + n2 * 2 + 3] & 0x7FFFFFF) - n2;
    }

    public final boolean isLeaf(int n) {
        return n % 2 == 1;
    }

    public final boolean isNode(int n) {
        return n < 536870912;
    }

    public final int makeLeaf(int n) {
        return (n << 1) + 1;
    }

    public NE rescan(NE nE, int n, int n2, byte[] arrby) {
        int n3;
        int n4 = this.depth(nE.n);
        while (n4 < n2 - n && this.isNode(n3 = this.getChild(nE.n, arrby[n + n4]))) {
            if (!this.isLeaf(n3) && this.depth(n3) <= n2 - n) {
                nE.n = n3;
                n4 = this.depth(nE.n);
                continue;
            }
            nE.e = n2 - n - n4;
            return nE;
        }
        nE.e = 0;
        return nE;
    }

    public NE scanPrefix(NE nE, int n, int n2, byte[] arrby) {
        int n3;
        int n4 = this.depth(nE.n);
        while (n4 < n2 - n && this.isNode(n3 = this.getChild(nE.n, arrby[n + n4]))) {
            int n5 = this.isLeaf(n3) ? arrby.length : this.depth(n3);
            int n6 = 0;
            while (n6 + n4 < n5 && n6 + n4 < n2 - n) {
                if (arrby[n + n4 + n6] == this.TERMINALCHAR || arrby[n + n4 + n6] != this.getByte(n3, n6 + n4)) {
                    nE.e = n6;
                    return nE;
                }
                nE.e = n6 + 1;
                ++n6;
            }
            if (!this.isLeaf(n3) && n5 <= n2 - n) {
                nE.n = n3;
                n4 = this.depth(nE.n);
                continue;
            }
            return nE;
        }
        nE.e = 0;
        return nE;
    }

    private final void setDistance(int n, int n2) {
        this.branches[n] = this.branches[n] & 0x7FFFFFF | n2 << 27;
    }

    private final void setFirstChild(int n, int n2) {
        this.branches[n] = this.branches[n] & 0xF8000000 | n2 & 0x7FFFFFF;
        this.branches[n + 1] = this.branches[n + 1] & 0x3FFFFFFF | n2 << 3 & 0xC0000000;
    }

    private void setNthChild(int n, int n2) {
        int n3 = this.firstChild(n);
        int n4 = this.depth(n);
        this.setSibling(n3, n2, n4);
        if (this.compare(this.getByte(n2, n4), this.getByte(n3, n4)) <= 0) {
            this.setFirstChild(n, n2);
        }
    }

    private void setSibling(int n, int n2, int n3) {
        int n4 = 536870912;
        byte by = this.getByte(n2, n3);
        while (this.isNode(n) && this.compare(this.getByte(n, n3), by) < 0) {
            n4 = n;
            n = this.siblingOrSuffixLink(n);
        }
        if (this.isNode(n4)) {
            this.setSiblingOrSuffixLink(n4, n2);
        }
        if (this.isNode(n) && this.compare(by, this.getByte(n, n3)) == 0 && by != this.TERMINALCHAR) {
            int n5 = this.siblingOrSuffixLink(n);
            this.setSiblingOrSuffixLink(n, 536870912);
            n = n5;
        }
        this.setSiblingOrSuffixLink(n2, n);
    }

    private final void setSiblingOrSuffixLink(int n, int n2) {
        if (this.isLeaf(n)) {
            this.leaves[n / 2] = this.leaves[n / 2] & 0xC0000000 | n2;
        } else {
            this.branches[n + 1] = this.branches[n + 1] & 0xC0000000 | n2;
        }
    }

    public final int siblingOrSuffixLink(int n) {
        n = this.isLeaf(n) ? this.leaves[n / 2] : this.branches[n + 1];
        return n & 0x3FFFFFFF;
    }

    public int suffixLink(int n) {
        if (this.distance(n) != 0) {
            return n + 2;
        }
        if ((this.branches[n + 2] & Integer.MIN_VALUE) == 0) {
            int n2 = this.firstChild(n);
            while (this.isNode(n2)) {
                n2 = this.siblingOrSuffixLink(n2);
            }
            return n2 & 0x1FFFFFFF;
        }
        return 2 * ((this.branches[n + 2] & 0x7FFFFC00) >>> 10 | (this.branches[n + 3] & 0xF8000000) >>> 6 | (this.leaves[this.headPosition(n)] & 0xC0000000) >>> 4);
    }

    public String nodeString(int n) {
        String string;
        if (this.isLeaf(n)) {
            string = "Leaf : " + n / 2;
        } else {
            string = "Node : " + n + " depth : " + this.depth(n) + ", headPosition : " + this.headPosition(n) + ", suffixLink : " + this.suffixLink(n) + " , distance : " + this.distance(n) + ", children : ";
            try {
                int n2 = this.firstChild(n);
                while (this.isNode(n2)) {
                    string = string + (char)this.getByte(n2, this.depth(n)) + "-p-" + n2 + " , ";
                    n2 = this.siblingOrSuffixLink(n2);
                }
            }
            catch (Exception exception) {
                Debug.pl(" caught exception in children " + exception);
            }
        }
        return string;
    }

    public String pathString(int n, int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        int n3 = this.isLeaf(n) ? n / 2 : this.headPosition(n);
        int n4 = n3 + n2;
        while (n3 < n4 && this.string[n3] != this.TERMINALCHAR) {
            stringBuffer.append(this.string[n3++]);
        }
        return stringBuffer.toString();
    }

    public String pathString(int n) {
        int n2;
        StringBuffer stringBuffer = new StringBuffer();
        int n3 = this.isLeaf(n) ? n / 2 : this.headPosition(n);
        int n4 = n2 = this.isLeaf(n) ? this.string.length : this.headPosition(n) + this.depth(n);
        while (n3 < n2 && this.string[n3] != this.TERMINALCHAR) {
            stringBuffer.append((char)this.string[n3++]);
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return this.toString(0, 0);
    }

    public String toString(int n, int n2) {
        int n3;
        int n4;
        String string = "";
        for (n4 = n2; n4 > 0; --n4) {
            string = string + "-";
        }
        n4 = this.depth(n);
        int n5 = this.headPosition(n);
        try {
            for (n3 = n2 + n5; n3 < n5 + n4; ++n3) {
                string = string + (char)this.string[n3];
            }
        }
        catch (RuntimeException runtimeException) {
            Debug.pl(" error , j : " + n2 + " , hp : " + n5 + " , d : " + n4 + " n : " + n);
            throw runtimeException;
        }
        n2 = string.length();
        n3 = this.firstChild(n);
        while (this.isNode(n3)) {
            if (this.isLeaf(n3)) {
                int n6;
                string = string + "\n";
                for (n6 = n2; n6 > 0; --n6) {
                    string = string + "-";
                }
                for (n6 = n2 + n3 / 2; n6 < this.string.length; ++n6) {
                    string = string + (char)this.string[n6];
                    if (this.string[n6] != this.TERMINALCHAR) {
                        continue;
                    }
                    break;
                }
            } else {
                string = string + "\n" + this.toString(n3, n2);
            }
            n3 = this.siblingOrSuffixLink(n3);
        }
        return string;
    }

    public void dropNodeEnds(int n, int n2) {
        int n3;
        if (!this.isLeaf(n) && (n3 = this.depth(n)) < n2) {
            int n4 = this.firstChild(n);
            int n5 = this.siblingOrSuffixLink(n4);
            this.dropNodeEnds(n4, n2);
            while (this.isNode(n5)) {
                if (this.getByte(n5, n3) == this.TERMINALCHAR) {
                    this.setSiblingOrSuffixLink(n4, n | 0x20000000);
                    break;
                }
                this.dropNodeEnds(n5, n2);
                n4 = n5;
                n5 = this.siblingOrSuffixLink(n5);
            }
        }
    }
    public static class NE {
        public int n = 0;
        public int e = 0;

        public NE(int n, int n2) {
            this.n = n;
            this.e = n2;
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds.wrappers;

public class MutableInteger
implements Comparable {
    public int i = 0;

    public MutableInteger(int n) {
        this.i = n;
    }

    public MutableInteger() {
    }

    public final int hashCode() {
        return this.i;
    }

    public final boolean equals(Object object) {
        return ((MutableInteger)object).i == this.i;
    }

    public final int compareTo(Object object) {
        int n = ((MutableInteger)object).i;
        return this.i == n ? 0 : (this.i > n ? 1 : -1);
    }

    public String toString() {
        return " " + this.i + " ";
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.ds.wrappers;

public class ObjectNode {
    public Object o;
    public ObjectNode pointer;
    private static ObjectNode stack = null;

    public ObjectNode() {
    }

    public ObjectNode(Object object, ObjectNode objectNode) {
        this.o = object;
        this.pointer = objectNode;
    }

    public ObjectNode end() {
        ObjectNode objectNode = this;
        ObjectNode objectNode2 = this.pointer;
        while (objectNode2 != null) {
            objectNode = objectNode2;
            objectNode2 = objectNode2.pointer;
        }
        return objectNode;
    }

    public static final ObjectNode getNode() {
        if (stack != null) {
            ObjectNode objectNode = stack;
            stack = ObjectNode.stack.pointer;
            return objectNode;
        }
        return new ObjectNode();
    }

    public static final ObjectNode getNode(Object object, ObjectNode objectNode) {
        ObjectNode objectNode2 = ObjectNode.getNode();
        objectNode2.set(object, objectNode);
        return objectNode2;
    }

    public static final void releaseNodes(ObjectNode objectNode, ObjectNode objectNode2) {
        objectNode2.pointer = stack;
        stack = objectNode;
    }

    public final void set(Object object, ObjectNode objectNode) {
        this.o = object;
        this.pointer = objectNode;
    }

    public String toString() {
        return " ( " + (this.pointer != null ? this.pointer.toString() : "") + " : " + (this.o != null ? this.o.toString() : "null") + " ) ";
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function {
    public Object fn(Object var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_2Args {
    public Object fn(Object var1, Object var2);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_3Args {
    public Object fn(Object var1, Object var2, Object var3);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_4Args {
    public Object fn(Object var1, Object var2, Object var3, Object var4);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Index {
    public Object fn(int var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Index_2Args {
    public Object fn(int var1, int var2);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Index_3Args {
    public Object fn(int var1, int var2, int var3);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Int {
    public int fn(int var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Int_2Args {
    public int fn(int var1, int var2);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Int_3Args {
    public int fn(int var1, int var2, int var3);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Int_4Args {
    public int fn(int var1, int var2, int var3, int var4);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Function_Int_5Args {
    public int fn(int var1, int var2, int var3, int var4, int var5);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function;
import bp.common.fp.Function_2Args;
import bp.common.fp.Function_3Args;
import bp.common.fp.Iterable;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Functions {
    public static Function doNothing() {
        return new Function(){

            public Object fn(Object object) {
                return object;
            }
        };
    }

    public static Function getIterator() {
        return new Function(){

            public Object fn(Object object) {
                return object instanceof Collection ? ((Collection)object).iterator() : ((Iterable)object).iterator();
            }
        };
    }

    public static Function constant(final Object object) {
        return new Function(){

            public Object fn(Object object2) {
                return object;
            }
        };
    }

    public static Function lCurry(final Function_2Args function_2Args, final Object object) {
        return new Function(){

            public Object fn(Object object2) {
                return function_2Args.fn(object, object2);
            }
        };
    }

    public static final Function parseNumber() {
        return new Function(){

            public Object fn(Object object) {
                return new Double((String)object);
            }
        };
    }

    public static Function pipe(final Function function, final Function function2) {
        return new Function(){

            public Object fn(Object object) {
                return function.fn(function2.fn(object));
            }
        };
    }

    public static Function rCurry(final Function_2Args function_2Args, final Object object) {
        return new Function(){

            public Object fn(Object object2) {
                return function_2Args.fn(object2, object);
            }
        };
    }

    public static final Function unpack(final Function_2Args function_2Args) {
        return new Function(){

            public Object fn(Object object) {
                return function_2Args.fn(((Object[])object)[0], ((Object[])object)[1]);
            }
        };
    }

    public static final Function unpack(final Function_3Args function_3Args) {
        return new Function(){

            public Object fn(Object object) {
                return function_3Args.fn(((Object[])object)[0], ((Object[])object)[1], ((Object[])object)[2]);
            }
        };
    }

    public static final Function abs() {
        return new Function(){

            public Object fn(Object object) {
                return new Double(Math.abs(((Number)object).doubleValue()));
            }
        };
    }

    public static final Function next() {
        return new Function(){

            public Object fn(Object object) {
                return ((Iterator)object).next();
            }
        };
    }

    public static Function pack() {
        return new Function(){

            public Object fn(Object object) {
                return new Object[]{object};
            }
        };
    }

    public static Function matrix1DLookUp(final double[] arrd) {
        return new Function(){

            public Object fn(Object object) {
                return new Double(arrd[((Number)object).intValue()]);
            }
        };
    }

    public static Function getString() {
        return new Function(){

            public Object fn(Object object) {
                return object.toString();
            }
        };
    }

    public static Function chunkLine(final Pattern pattern) {
        return new Function(){

            public Object fn(Object object) {
                String string = (String)object;
                return pattern.split(string);
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function;
import bp.common.fp.Function_2Args;
import bp.common.fp.Function_3Args;
import bp.common.fp.Predicate;
import java.util.Collection;
import java.util.Map;

public class Functions_2Args {
    public static final Function_2Args append() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                ((Collection)object2).add(object);
                return object2;
            }
        };
    }

    public static final Function_2Args appendCollection() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                ((Collection)object2).addAll((Collection)object);
                return object2;
            }
        };
    }

    public static Function_2Args concatenateIntArrays() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                int[] arrn = (int[])object;
                int[] arrn2 = (int[])object2;
                int[] arrn3 = new int[arrn.length + arrn2.length];
                System.arraycopy(arrn, 0, arrn3, 0, arrn.length);
                System.arraycopy(arrn2, 0, arrn3, arrn.length, arrn2.length);
                return arrn3;
            }
        };
    }

    public static final Function_2Args concatenate() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return (String)object + (String)object2;
            }
        };
    }

    public static final Function_2Args divide() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return new Double(((Number)object).doubleValue() / ((Number)object2).doubleValue());
            }
        };
    }

    public static final Function_2Args lCurry(final Function_3Args function_3Args, final Object object) {
        return new Function_2Args(){

            public Object fn(Object object3, Object object2) {
                return function_3Args.fn(object, object3, object2);
            }
        };
    }

    public static final Function_2Args leftArg() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return object;
            }
        };
    }

    public static final Function_2Args lPipe(final Function_2Args function_2Args, final Function function) {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return function_2Args.fn(function.fn(object), object2);
            }
        };
    }

    public static final Function_2Args map() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return ((Map)object2).get(object);
            }
        };
    }

    public static final Function_2Args max() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return ((Comparable)object).compareTo(object2) > 0 ? object : object2;
            }
        };
    }

    public static final Function_2Args min() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return ((Comparable)object).compareTo(object2) < 0 ? object : object2;
            }
        };
    }

    public static final Function_2Args multiply() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return new Double(((Number)object).doubleValue() * ((Number)object2).doubleValue());
            }
        };
    }

    public static final Function_2Args pipe(final Function function, final Function_2Args function_2Args) {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return function.fn(function_2Args.fn(object, object2));
            }
        };
    }

    public static final Function_2Args predicateWrapper() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return ((Predicate)object2).test(object) ? Boolean.TRUE : Boolean.FALSE;
            }
        };
    }

    public static final Function_2Args rCurry(final Function_3Args function_3Args, final Object object) {
        return new Function_2Args(){

            public Object fn(Object object3, Object object2) {
                return function_3Args.fn(object3, object2, object);
            }
        };
    }

    public static final Function_2Args rightArg() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return object2;
            }
        };
    }

    public static final Function_2Args rPipe(final Function_2Args function_2Args, final Function function) {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return function_2Args.fn(object, function.fn(object2));
            }
        };
    }

    public static final Function_2Args subtract() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return new Double(((Number)object).doubleValue() - ((Number)object2).doubleValue());
            }
        };
    }

    public static final Function_2Args sum() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return new Double(((Number)object).doubleValue() + ((Number)object2).doubleValue());
            }
        };
    }

    public static Function_2Args concatenateArrays() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                Object[] arrobject = (Object[])object;
                Object[] arrobject2 = (Object[])object2;
                Object[] arrobject3 = new Object[arrobject.length + arrobject2.length];
                System.arraycopy(arrobject, 0, arrobject3, 0, arrobject.length);
                System.arraycopy(arrobject2, 0, arrobject3, arrobject.length, arrobject2.length);
                return arrobject3;
            }
        };
    }

    public static Function_2Args sumLogs() {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                double d;
                double d2 = ((Number)object).doubleValue();
                if (d2 > (d = ((Number)object2).doubleValue())) {
                    double d3 = d - d2;
                    return new Double(d2 + Math.log(1.0 + Math.exp(d3)));
                }
                double d4 = d2 - d;
                return new Double(d + Math.log(1.0 + Math.exp(d4)));
            }
        };
    }

    public static Function_2Args matrix2DLookup(final double[] arrd, final int n) {
        return new Function_2Args(){

            public Object fn(Object object, Object object2) {
                int n3 = ((Number)object).intValue();
                int n2 = ((Number)object2).intValue();
                return new Double(arrd[n2 * n + n3]);
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function;
import bp.common.fp.Function_2Args;
import bp.common.fp.Function_3Args;
import bp.common.fp.Function_4Args;

public class Functions_3Args {
    public static final Function_3Args leftArg() {
        return new Function_3Args(){

            public Object fn(Object object, Object object2, Object object3) {
                return object;
            }
        };
    }

    public static final Function_3Args rightArg() {
        return new Function_3Args(){

            public Object fn(Object object, Object object2, Object object3) {
                return object3;
            }
        };
    }

    public static final Function_3Args middleArg() {
        return new Function_3Args(){

            public Object fn(Object object, Object object2, Object object3) {
                return object2;
            }
        };
    }

    public static final Function_3Args pipe(final Function function, final Function_3Args function_3Args) {
        return new Function_3Args(){

            public Object fn(Object object, Object object2, Object object3) {
                return function.fn(function_3Args.fn(object, object2, object3));
            }
        };
    }

    public static final Function_3Args rCurry(final Function_4Args function_4Args, final Object object) {
        return new Function_3Args(){

            public Object fn(Object object4, Object object2, Object object3) {
                return function_4Args.fn(object4, object2, object3, object);
            }
        };
    }

    public static final Function_3Args lCurry(final Function_4Args function_4Args, final Object object) {
        return new Function_3Args(){

            public Object fn(Object object4, Object object2, Object object3) {
                return function_4Args.fn(object, object4, object2, object3);
            }
        };
    }

    public static final Function_3Args concatenate() {
        return new Function_3Args(){

            public Object fn(Object object, Object object2, Object object3) {
                return (String)object + (String)object2 + (String)object3;
            }
        };
    }

    public static Function_3Args flipArguments() {
        return new Function_3Args(){

            public Object fn(Object object, Object object2, Object object3) {
                return ((Function_2Args)object3).fn(object2, object);
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function_Int_2Args;

public final class Functions_Int_2Args {
    public static final Function_Int_2Args sum() {
        return new Function_Int_2Args(){

            public int fn(int n, int n2) {
                return n + n2;
            }
        };
    }

    public static final Function_Int_2Args max() {
        return new Function_Int_2Args(){

            public final int fn(int n, int n2) {
                return n > n2 ? n : n2;
            }
        };
    }

    public static final Function_Int_2Args min() {
        return new Function_Int_2Args(){

            public final int fn(int n, int n2) {
                return n < n2 ? n : n2;
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Generator {
    public Object gen();
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Generator_Int {
    public int gen();
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Generator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeneratorIterator
implements Iterator {
    Generator gen;
    Object o;
    boolean notChecked = true;

    public GeneratorIterator(Generator generator) {
        this.gen = generator;
        this.o = generator.gen();
    }

    public boolean hasNext() {
        return this.o != null;
    }

    public Object next() {
        if (this.o == null) {
            throw new NoSuchElementException();
        }
        Object object = this.o;
        this.o = this.gen.gen();
        return object;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.ds.ScrollingQueue;
import bp.common.fp.Function;
import bp.common.fp.Generator;
import bp.common.fp.Predicate;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public final class Generators {
    public static final Generator arrayGenerator(final Object[] arrobject) {
        return new Generator(){
            int i = 0;

            public final Object gen() {
                return this.i < arrobject.length ? arrobject[this.i++] : null;
            }
        };
    }

    public static final Generator constant(final Object object) {
        return new Generator(){

            public final Object gen() {
                return object;
            }
        };
    }

    public static final Generator append(final Generator generator, final Generator generator2) {
        return new Generator(){
            Generator active;
            {
                this.active = generator;
            }

            public final Object gen() {
                Object object = this.active.gen();
                if (object == null) {
                    this.active = generator2;
                    return this.active.gen();
                }
                return object;
            }
        };
    }

    public static final Generator iteratorGenerator(final Iterator iterator) {
        return new Generator(){

            public final Object gen() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }

    public static final Generator map(final Generator generator, final Function function) {
        return new Generator(){

            public final Object gen() {
                Object object = generator.gen();
                return object != null ? function.fn(object) : null;
            }
        };
    }

    public static final Generator queueGenerator(final List list) {
        return new Generator(){

            public final Object gen() {
                return list.size() != 0 ? list.remove(0) : null;
            }
        };
    }

    public static Generator filter(final Generator generator, final Predicate predicate) {
        return new Generator(){

            public final Object gen() {
                Object object = generator.gen();
                while (object != null) {
                    if (predicate.test(object)) {
                        return object;
                    }
                    object = generator.gen();
                }
                return null;
            }
        };
    }

    public static final Generator[] splitGenerator(Generator generator, Function function) {
        ScrollingQueue scrollingQueue = new ScrollingQueue(100, 0);
        ScrollingQueue scrollingQueue2 = new ScrollingQueue(100, 0);
        return new Generator[]{Generators.feedGenerator(generator, scrollingQueue, scrollingQueue2, function), Generators.feedGenerator(generator, scrollingQueue2, scrollingQueue, function)};
    }

    public static final Generator feedGenerator(final Generator generator, final ScrollingQueue scrollingQueue, final ScrollingQueue scrollingQueue2, final Function function) {
        return new Generator(){

            public final Object gen() {
                if (scrollingQueue.size() > 0) {
                    return scrollingQueue.removeFirst();
                }
                Object object = generator.gen();
                if (object != null) {
                    scrollingQueue2.add(function.fn(object));
                }
                return object;
            }
        };
    }

    public static Generator lineGenerator(final Reader reader) {
        return new Generator(){
            LineNumberReader lNR;
            {
                this.lNR = new LineNumberReader(reader);
            }

            public Object gen() {
                try {
                    String string = this.lNR.readLine();
                    return string;
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    throw new IllegalStateException();
                }
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Generator_Int;

public final class Generators_Int {
    public static final Generator_Int constant(final int n) {
        return new Generator_Int(){

            public int gen() {
                return n;
            }
        };
    }

    public static Generator_Int arrayGen(final int[] arrn, final int n) {
        return new Generator_Int(){
            int i = 0;

            public int gen() {
                if (this.i < n) {
                    return arrn[this.i++];
                }
                return Integer.MAX_VALUE;
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Generator;
import java.util.Collection;

public final class GeneratorTools {
    public static final Collection append(Generator generator, Collection collection) {
        Object object;
        while ((object = generator.gen()) != null) {
            collection.add(object);
        }
        return collection;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import java.util.Iterator;

public interface Iterable {
    public Iterator iterator();
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function_2Args;
import bp.common.fp.Functions;
import bp.common.fp.Functions_2Args;
import bp.common.fp.Functions_3Args;
import bp.common.fp.Generator;
import bp.common.fp.GeneratorIterator;
import bp.common.fp.Generators;
import bp.common.fp.Iterators;
import bp.common.fp.Predicate_2Args;
import bp.common.fp.Predicates;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class IterationTools {
    public static final Iterator addToEnd(Iterator iterator, Object object) {
        return Iterators.chain(Arrays.asList(iterator, Arrays.asList(object).iterator()).iterator());
    }

    public static final Double noOfElements(Iterator iterator) {
        return IterationTools.sum(Iterators.map(iterator, Functions.constant(new Integer(1))));
    }

    public static final Double sum(Iterator iterator) {
        return (Double)IterationTools.reduce(iterator, new Double(0.0), Functions_2Args.sum());
    }

    public static final Collection append(Iterator iterator, Collection collection) {
        while (iterator.hasNext()) {
            collection.add(iterator.next());
        }
        return collection;
    }

    public static final boolean equals(Iterator iterator, Iterator iterator2, Predicate_2Args predicate_2Args) {
        return !Iterators.filter(Iterators.zip(iterator, iterator2, Object.class), Predicates.inverse(Predicates.unpack(predicate_2Args))).hasNext() && !iterator.hasNext() && !iterator2.hasNext();
    }

    public static final Object reduce(Iterator iterator, Object object, Function_2Args function_2Args) {
        while (iterator.hasNext()) {
            object = function_2Args.fn(iterator.next(), object);
        }
        return object;
    }

    public static final String join(Object[] arrobject, String string) {
        return (String)IterationTools.reduce(new GeneratorIterator(Generators.map(Generators.arrayGenerator(arrobject), Functions.getString())), string, Functions_2Args.rCurry(Functions_3Args.flipArguments(), Functions_2Args.pipe(Functions.rCurry(Functions_2Args.concatenate(), string), Functions_2Args.concatenate())));
    }

    public static final String join(int[] arrn, String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        for (int n : arrn) {
            stringBuffer.append(n);
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static final String join(double[] arrd, String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        for (double d : arrd) {
            stringBuffer.append(d);
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static final String join(float[] arrf, String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        for (float f : arrf) {
            stringBuffer.append(f);
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static final String join(boolean[] arrbl, String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        for (boolean bl : arrbl) {
            stringBuffer.append(bl);
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function;
import bp.common.fp.Function_2Args;
import bp.common.fp.Generator;
import bp.common.fp.GeneratorIterator;
import bp.common.fp.IterationTools;
import bp.common.fp.Predicate;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Iterators {
    public static final Iterator uniq(final Iterator iterator, final Comparator comparator, final Function_2Args function_2Args) {
        return new Iterator(){
            Object p = null;
            Object p2;
            {
                if (iterator.hasNext()) {
                    this.p2 = iterator.next();
                }
                this.getNext();
            }

            public final boolean hasNext() {
                return this.p != null;
            }

            private final void getNext() {
                this.p = this.p2;
                if (iterator.hasNext()) {
                    this.p2 = iterator.next();
                    if (comparator.compare(this.p, this.p2) == 0) {
                        this.p2 = function_2Args.fn(this.p, this.p2);
                        this.getNext();
                    }
                } else {
                    this.p2 = null;
                }
            }

            public Object next() {
                if (this.p == null) {
                    throw new NoSuchElementException();
                }
                Object object = this.p;
                this.getNext();
                return object;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static final Iterator merge(final Iterator iterator, final Iterator iterator2, final Comparator comparator) {
        return new Iterator(){
            Object o;
            Object o2;
            Object o3;
            {
                if (iterator.hasNext()) {
                    this.o = iterator.next();
                }
                if (iterator2.hasNext()) {
                    this.o2 = iterator2.next();
                }
                this.getNext();
            }

            public final boolean hasNext() {
                return this.o3 != null;
            }

            private final void getNext() {
                if (this.o != null) {
                    if (this.o2 != null) {
                        int n = comparator.compare(this.o, this.o2);
                        if (n <= 0) {
                            this.o3 = this.o;
                            this.o = iterator.hasNext() ? iterator.next() : null;
                        } else {
                            this.o3 = this.o2;
                            this.o2 = iterator2.hasNext() ? iterator2.next() : null;
                        }
                    } else {
                        this.o3 = this.o;
                        this.o = iterator.hasNext() ? iterator.next() : null;
                    }
                } else if (this.o2 != null) {
                    this.o3 = this.o2;
                    this.o2 = iterator2.hasNext() ? iterator2.next() : null;
                } else {
                    this.o3 = null;
                }
            }

            public Object next() {
                if (this.o3 == null) {
                    throw new NoSuchElementException();
                }
                Object object = this.o3;
                this.getNext();
                return object;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static final Iterator count(final int n) {
        return new Iterator(){
            int j;
            {
                this.j = n;
            }

            public final boolean hasNext() {
                return true;
            }

            public final Object next() {
                return new Integer(this.j++);
            }

            public final void remove() {
                throw new IllegalStateException("Meaningless operation");
            }
        };
    }

    public static final Iterator chain(final Iterator iterator) {
        return new Iterator(){
            final Object END = new Object[0];
            Iterator it2;
            Object o;
            {
                if (iterator.hasNext()) {
                    this.it2 = (Iterator)iterator.next();
                    this.getNext();
                } else {
                    this.o = this.END;
                }
            }

            public final void getNext() {
                if (this.it2.hasNext()) {
                    this.o = this.it2.next();
                    return;
                }
                if (iterator.hasNext()) {
                    this.it2 = (Iterator)iterator.next();
                    this.getNext();
                    return;
                }
                this.o = this.END;
            }

            public final boolean hasNext() {
                return this.o != this.END;
            }

            public final Object next() {
                if (this.o == this.END) {
                    throw new NoSuchElementException();
                }
                Object object = this.o;
                this.getNext();
                return object;
            }

            public final void remove() {
                throw new RuntimeException("Not implemented");
            }
        };
    }

    public static final Iterator filter(final Iterator iterator, final Predicate predicate) {
        return new GeneratorIterator(new Generator(){

            public final Object gen() {
                while (iterator.hasNext()) {
                    Object e = iterator.next();
                    if (!predicate.test(e)) continue;
                    return e;
                }
                return null;
            }
        });
    }

    public static final Iterator map(final Iterator iterator, final Function function) {
        return new Iterator(){

            public final boolean hasNext() {
                return iterator.hasNext();
            }

            public final Object next() {
                return function.fn(iterator.next());
            }

            public final void remove() {
                throw new RuntimeException("Not implemented");
            }
        };
    }

    public static final Iterator zip(final Iterator iterator, final Iterator iterator2, final Class class_) {
        return new Iterator(){

            public boolean hasNext() {
                return iterator.hasNext() && iterator2.hasNext();
            }

            public final Object next() {
                Object[] arrobject = (Object[])Array.newInstance(class_, 2);
                arrobject[0] = iterator.next();
                arrobject[1] = iterator2.next();
                return arrobject;
            }

            public final void remove() {
                throw new RuntimeException("Not yet implemented");
            }
        };
    }

    public static final Iterator repeat(final Object object, final int n) {
        return new Iterator(){
            int i;
            {
                this.i = n;
            }

            public final boolean hasNext() {
                return this.i > 0;
            }

            public final Object next() {
                if (this.i-- <= 0) {
                    throw new NoSuchElementException();
                }
                return object;
            }

            public final void remove() {
                throw new RuntimeException("Not yet implemented");
            }
        };
    }

    public static final Iterator sort(Iterator iterator, Comparator comparator) {
        List list = (List)IterationTools.append(iterator, new ArrayList());
        Collections.sort(list, comparator);
        return list.iterator();
    }

    public static final Iterator[] duplicate(final Iterator<Integer> iterator) {
        final LinkedList linkedList = new LinkedList();
        final LinkedList linkedList2 = new LinkedList();
        Generator generator = new Generator(){

            public Object gen() {
                if (linkedList2.size() == 0) {
                    if (iterator.hasNext()) {
                        Object e = iterator.next();
                        linkedList.add(e);
                        return e;
                    }
                    return null;
                }
                return linkedList2.remove(0);
            }
        };
        Generator generator2 = new Generator(){

            public Object gen() {
                if (linkedList.size() == 0) {
                    if (iterator.hasNext()) {
                        Object e = iterator.next();
                        linkedList2.add(e);
                        return e;
                    }
                    return null;
                }
                return linkedList.remove(0);
            }
        };
        return new Iterator[]{new GeneratorIterator(generator), new GeneratorIterator(generator2)};
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Predicate {
    public boolean test(Object var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Predicate_2Args {
    public boolean test(Object var1, Object var2);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Predicate_3Args {
    public boolean test(Object var1, Object var2, Object var3);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Predicate_Double_2Args {
    public boolean test(double var1, double var3);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Predicate_Int {
    public boolean test(int var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Function;
import bp.common.fp.Predicate;
import bp.common.fp.Predicate_2Args;
import java.util.HashSet;
import java.util.Set;

public final class Predicates {
    public static Predicate alwaysFalse() {
        return new Predicate(){

            public boolean test(Object object) {
                return false;
            }
        };
    }

    public static Predicate alwaysTrue() {
        return new Predicate(){

            public boolean test(Object object) {
                return true;
            }
        };
    }

    public static Predicate and(final Predicate predicate, final Predicate predicate2) {
        return new Predicate(){

            public boolean test(Object object) {
                return predicate.test(object) && predicate2.test(object);
            }
        };
    }

    public static Predicate inverse(final Predicate predicate) {
        return new Predicate(){

            public boolean test(Object object) {
                return !predicate.test(object);
            }
        };
    }

    public static Predicate lCurry(final Predicate_2Args predicate_2Args, final Object object) {
        return new Predicate(){

            public boolean test(Object object2) {
                return predicate_2Args.test(object, object2);
            }
        };
    }

    public static Predicate nAnd(final Predicate predicate, final Predicate predicate2) {
        return new Predicate(){

            public boolean test(Object object) {
                return !predicate.test(object) && !predicate2.test(object);
            }
        };
    }

    public static Predicate or(final Predicate predicate, final Predicate predicate2) {
        return new Predicate(){

            public boolean test(Object object) {
                return predicate.test(object) || predicate2.test(object);
            }
        };
    }

    public static final Predicate pipe(final Predicate predicate, final Function function) {
        return new Predicate(){

            public boolean test(Object object) {
                return predicate.test(function.fn(object));
            }
        };
    }

    public static Predicate rCurry(final Predicate_2Args predicate_2Args, final Object object) {
        return new Predicate(){

            public boolean test(Object object2) {
                return predicate_2Args.test(object2, object);
            }
        };
    }

    public static Predicate seen() {
        return new Predicate(){
            Set<Object> set = new HashSet<Object>();

            public boolean test(Object object) {
                if (this.set.contains(object)) {
                    return true;
                }
                this.set.add(object);
                return false;
            }
        };
    }

    public static final Predicate sumIsGreaterThan(final double d) {
        return new Predicate(){
            double sum = 0.0;

            public final boolean test(Object object) {
                this.sum += ((Number)object).doubleValue();
                return this.sum > d;
            }
        };
    }

    public static final Predicate sumIsLessThan(final double d) {
        return new Predicate(){
            double sum = 0.0;

            public final boolean test(Object object) {
                this.sum += ((Number)object).doubleValue();
                return this.sum < d;
            }
        };
    }

    public static Predicate trueXTimes(final int n) {
        return new Predicate(){
            int i = 0;

            public boolean test(Object object) {
                return this.i++ < n;
            }
        };
    }

    public static Predicate unpack(final Predicate_2Args predicate_2Args) {
        return new Predicate(){

            public boolean test(Object object) {
                return predicate_2Args.test(((Object[])object)[0], ((Object[])object)[1]);
            }
        };
    }

    public static final Predicate window(final Predicate_2Args predicate_2Args, final Object object) {
        return new Predicate(){
            Object o;
            {
                this.o = object;
            }

            public final boolean test(Object object2) {
                boolean bl = predicate_2Args.test(this.o, object2);
                this.o = object2;
                return bl;
            }
        };
    }

    public static Predicate xOr(final Predicate predicate, final Predicate predicate2) {
        return new Predicate(){

            public boolean test(Object object) {
                return predicate.test(object) ^ predicate2.test(object);
            }
        };
    }

    public static Predicate indexInArray(final int[] arrn, final int n) {
        return new Predicate(){
            int i = 0;
            int j = 0;

            public boolean test(Object object) {
                if (this.i < n && arrn[this.i] == this.j++) {
                    ++this.i;
                    return true;
                }
                return false;
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure {
    public void pro(Object var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure_2Args {
    public void pro(Object var1, Object var2);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure_Int {
    public void pro(int var1);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure_Int_2Args {
    public void pro(int var1, int var2);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure_Int_3Args {
    public void pro(int var1, int var2, int var3);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure_Int_5Args {
    public void pro(int var1, int var2, int var3, int var4, int var5);
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

public interface Procedure_NoArgs {
    public void pro();
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Procedure;

public class Procedures {
    public static Procedure runProcedures(final Procedure[] arrprocedure) {
        return new Procedure(){

            public void pro(Object object) {
                for (Procedure procedure : arrprocedure) {
                    procedure.pro(object);
                }
            }
        };
    }

    public static Procedure doNothing() {
        return new Procedure(){

            public void pro(Object object) {
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.fp;

import bp.common.fp.Procedure_Int;

public class Procedures_Int {
    public static Procedure_Int doNothing() {
        return new Procedure_Int(){

            public void pro(int n) {
            }
        };
    }

    public static Procedure_Int runProcedures(final Procedure_Int[] arrprocedure_Int) {
        return new Procedure_Int(){

            public void pro(int n) {
                for (Procedure_Int procedure_Int : arrprocedure_Int) {
                    procedure_Int.pro(n);
                }
            }
        };
    }

    public static Procedure_Int uptoAndIncluding(final Procedure_Int procedure_Int, final int n) {
        return new Procedure_Int(){
            int i;
            {
                this.i = n;
            }

            public void pro(int n2) {
                while (this.i <= n2) {
                    procedure_Int.pro(this.i++);
                }
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.Generator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ArraysToFile {
    public static final int writeOutArray(Generator generator, OutputStream outputStream) throws IOException {
        int[] arrn;
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        int n = 0;
        while ((arrn = (int[])generator.gen()) != null) {
            for (int n2 : arrn) {
                dataOutputStream.writeInt(n2);
            }
            ++n;
        }
        dataOutputStream.flush();
        return n;
    }

    public static final Generator readInArray(final InputStream inputStream, final int n, final int n2) {
        return new Generator(){
            DataInputStream dIS;
            int j;
            {
                this.dIS = new DataInputStream(inputStream);
                this.j = n;
            }

            public final Object gen() {
                if (this.j-- > 0) {
                    try {
                        int[] arrn = new int[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrn[i] = this.dIS.readInt();
                        }
                        return arrn;
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                        throw new IllegalStateException();
                    }
                }
                return null;
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.Generator;
import bp.pecan.PolygonFiller;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CigarParser_Generator {
    static Pattern p = Pattern.compile("cigar:\\s+(.+)\\s+([0-9]+)\\s+([0-9]+)\\s+([\\+\\-\\.])\\s+(.+)\\s+([0-9]+)\\s+([0-9]+)\\s+([\\+\\-\\.])\\s+([0-9]+)(\\s+(.*)\\s*)*");

    public static Cigar parseCigar(LineNumberReader lineNumberReader) throws IOException {
        String string;
        while ((string = lineNumberReader.readLine()) != null) {
            int[] arrn;
            Matcher matcher = p.matcher(string);
            if (!matcher.matches()) continue;
            if (matcher.group(11) != null) {
                String[] arrstring = matcher.group(11).split(" ");
                arrn = new int[arrstring.length];
                for (int i = 0; i < arrn.length; i += 2) {
                    if (arrstring[i].length() != 1) {
                        throw new IllegalArgumentException();
                    }
                    switch (arrstring[i].charAt(0)) {
                        case 'M': {
                            arrn[i] = 0;
                            break;
                        }
                        case 'I': {
                            arrn[i] = 1;
                            break;
                        }
                        case 'D': {
                            arrn[i] = 2;
                            break;
                        }
                        default: {
                            throw new IllegalStateException();
                        }
                    }
                    arrn[i + 1] = Integer.parseInt(arrstring[i + 1]);
                }
            } else {
                arrn = new int[]{};
            }
            return new Cigar(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), CigarParser_Generator.getStrandCode(matcher.group(4)), matcher.group(5), Integer.parseInt(matcher.group(6)), Integer.parseInt(matcher.group(7)), CigarParser_Generator.getStrandCode(matcher.group(8)), Integer.parseInt(matcher.group(9)), arrn);
        }
        return null;
    }

    public static int getStrandCode(String string) {
        if (string.equals("+")) {
            return 0;
        }
        if (string.equals("-")) {
            return 1;
        }
        if (string.equals(".")) {
            return 2;
        }
        throw new IllegalArgumentException();
    }

    public static Generator convertToEdgeList(final Cigar cigar) {
        return new Generator(){
            int indexSeq1;
            int indexSeq2;
            int operationIndex;
            {
                this.indexSeq1 = cigar.queryStart;
                this.indexSeq2 = cigar.targetStart;
            }

            public Object gen() {
                if (this.operationIndex < cigar.operations.length) {
                    int n = cigar.operations[this.operationIndex];
                    int n2 = cigar.operations[this.operationIndex + 1];
                    this.operationIndex += 2;
                    if (n2 == 0) {
                        return this.gen();
                    }
                    switch (n) {
                        case 0: {
                            PolygonFiller.Node node = new PolygonFiller.Node(this.indexSeq1, this.indexSeq2, this.indexSeq2 + n2 - 1, 1);
                            this.indexSeq1 += n2;
                            this.indexSeq2 += n2;
                            return node;
                        }
                        case 1: {
                            this.indexSeq1 += n2;
                            return this.gen();
                        }
                        case 2: {
                            this.indexSeq2 += n2;
                            return this.gen();
                        }
                    }
                    throw new IllegalArgumentException();
                }
                return null;
            }
        };
    }
    public static class Cigar {
        public static final int MATCH = 0;
        public static final int INSERT = 1;
        public static final int DELETE = 2;
        public String queryId;
        public String targetId;
        public int queryStart;
        public int queryEnd;
        public int targetStart;
        public int targetEnd;
        public int score;
        public static final int PLUS = 0;
        public static final int MINUS = 1;
        public static final int NA = 2;
        public int queryStrand;
        public int targetStrand;
        public int[] operations;

        public Cigar(String string, int n, int n2, int n3, String string2, int n4, int n5, int n6, int n7, int[] arrn) {
            this.queryId = string;
            this.queryStart = n;
            this.queryEnd = n2;
            this.queryStrand = n3;
            this.targetId = string2;
            this.targetStart = n4;
            this.targetEnd = n5;
            this.targetStrand = n6;
            this.score = n7;
            this.operations = arrn;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("cigar: " + this.queryId + " " + this.queryStart + " " + this.queryEnd + " " + (this.queryStrand == 0 ? "+" : (this.queryStrand == 1 ? "-" : ".")) + " " + this.targetId + " " + this.targetStart + " " + this.targetEnd + " " + (this.targetStrand == 0 ? "+" : (this.targetStrand == 1 ? "-" : ".")) + " " + this.score);
            block5: for (int i = 0; i < this.operations.length; i += 2) {
                switch (this.operations[i]) {
                    case 0: {
                        stringBuffer.append(" M " + this.operations[i + 1]);
                        continue block5;
                    }
                    case 1: {
                        stringBuffer.append(" I " + this.operations[i + 1]);
                        continue block5;
                    }
                    case 2: {
                        stringBuffer.append(" D " + this.operations[i + 1]);
                    }
                }
            }
            return stringBuffer.toString();
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import java.io.PrintStream;

public final class Debug {
    public static boolean ON = true;
    public static boolean STOUT = false;
    public static final boolean DEBUGCODE = true;

    public static void pl(String string) {
        if (ON) {
            if (STOUT) {
                System.out.println(string);
            } else {
                System.err.println(string);
            }
        }
    }

    public static void p(String string) {
        if (ON) {
            if (STOUT) {
                System.out.print(string);
            } else {
                System.err.print(string);
            }
        }
    }
    public static class P {
        public static void pl(String string) {
            if (ON) {
                System.err.println(string);
            }
        }

        public static void p(String string) {
            if (ON) {
                System.err.print(string);
            }
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;

public class ExternalExecution {
    public static int runSaveOutput(String[] arrstring, OutputStream outputStream, String[] arrstring2) throws IOException {
        Process process = Runtime.getRuntime().exec(arrstring, arrstring2);
        InputStream inputStream = process.getInputStream();
        InputStream inputStream2 = process.getErrorStream();
        OutputStream outputStream2 = process.getOutputStream();
        StreamPump streamPump = new StreamPump(inputStream, outputStream);
        StreamPump streamPump2 = new StreamPump(inputStream2, System.err);
        streamPump.start();
        streamPump2.start();
        try {
            process.waitFor();
        }
        catch (InterruptedException interruptedException) {
            return -1;
        }
        while (streamPump.isAlive() || streamPump2.isAlive()) {
        }
        inputStream.close();
        inputStream2.close();
        outputStream2.close();
        outputStream.flush();
        return process.exitValue();
    }

    public static String getAbsolutePath(String string) {
        URL uRL = ClassLoader.getSystemResource(string);
        return uRL != null ? uRL.getFile() : null;
    }

    public static String createFileOnClassPath(String string) throws IOException {
        String string2 = ExternalExecution.getAbsolutePath(string);
        if (string2 != null) {
            return string2;
        }
        string2 = ExternalExecution.getAbsolutePath(new File(string).getParent());
        if (string2 != null) {
            File file = new File(string2, new File(string).getName());
            file.createNewFile();
            return file.getAbsolutePath();
        }
        return null;
    }
    static class StreamPump
    extends Thread {
        InputStream iS;
        OutputStream oS;

        StreamPump(InputStream inputStream, OutputStream outputStream) {
            this.iS = inputStream;
            this.oS = outputStream;
        }

        public void run() {
            try {
                int n;
                while ((n = this.iS.read()) != -1) {
                    this.oS.write(n);
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.Generator_Int;
import bp.common.fp.Procedure_Int;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class FastaOutput_Procedure_Int
implements Procedure_Int {
    private OutputStream oS;
    private int index = 0;
    private static final int lineWidth = 60;

    public FastaOutput_Procedure_Int(OutputStream outputStream, String string) throws IOException {
        byte[] arrby;
        this.oS = outputStream;
        outputStream.write(62);
        for (byte by : arrby = string.getBytes()) {
            outputStream.write(by);
        }
        outputStream.write(10);
    }

    public void pro(int n) {
        try {
            if (this.index >= 60) {
                this.oS.write(10);
                this.oS.write((char)n);
                this.index = 1;
            } else {
                this.oS.write((char)n);
                ++this.index;
            }
        }
        catch (IOException iOException) {
            throw new IllegalStateException();
        }
    }

    public void end() throws IOException {
        this.oS.write(10);
        this.oS.flush();
    }

    public void endAndClose() throws IOException {
        this.end();
        this.oS.close();
    }

    public static final void writeFile(OutputStream outputStream, String string, byte[] arrby, int n, int n2) throws FileNotFoundException, IOException {
        FastaOutput_Procedure_Int fastaOutput_Procedure_Int = new FastaOutput_Procedure_Int(outputStream, string);
        for (int i = n; i < n + n2; ++i) {
            fastaOutput_Procedure_Int.pro(arrby[i]);
        }
        fastaOutput_Procedure_Int.end();
    }

    public static final void writeFile(OutputStream outputStream, String string, Generator_Int generator_Int, int n) throws FileNotFoundException, IOException {
        int n2;
        FastaOutput_Procedure_Int fastaOutput_Procedure_Int = new FastaOutput_Procedure_Int(outputStream, string);
        while ((n2 = generator_Int.gen()) != n) {
            fastaOutput_Procedure_Int.pro(n2);
        }
        fastaOutput_Procedure_Int.end();
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.Generator_Int;
import java.io.IOException;
import java.io.InputStream;

public class FastaParser_Generator_Int
implements Generator_Int {
    private final InputStream iS;
    private String currentTitle = null;
    private final int reservedNumber;
    private boolean newSequence = true;

    public FastaParser_Generator_Int(InputStream inputStream, int n) {
        this.iS = inputStream;
        this.reservedNumber = n;
        this.gen();
        if (this.currentTitle == null) {
            throw new IllegalStateException();
        }
    }

    private static String getTitle(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int n;
            while ((n = inputStream.read()) != 10 && n != -1) {
                stringBuffer.append((char)n);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            throw new IllegalStateException(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }

    public final int gen() {
        int n;
        try {
            n = this.iS.read();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            throw new IllegalStateException();
        }
        switch (n) {
            case 10: {
                return this.gen();
            }
            case 62: {
                this.newSequence = true;
                this.currentTitle = FastaParser_Generator_Int.getTitle(this.iS);
                return this.reservedNumber;
            }
            case -1: {
                this.newSequence = false;
                return this.reservedNumber;
            }
        }
        return n;
    }

    public final String getFastaID() {
        return this.currentTitle;
    }

    public static int readFile(FastaParser_Generator_Int fastaParser_Generator_Int, int n, byte[] arrby, int n2) {
        int n3;
        while ((n3 = fastaParser_Generator_Int.gen()) != n) {
            arrby[n2++] = (byte)n3;
        }
        return n2;
    }

    public boolean isNewSequence() {
        boolean bl = this.newSequence;
        this.newSequence = false;
        return bl;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.IterationTools;
import bp.common.io.Debug;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMunger {
    private static final Logger logger = Logger.getLogger(InputMunger.class.getName());
    static final Pattern helpPattern = Pattern.compile("[\\s]*-+[hH][Ee][lL][pP][\\s]*");
    private static final String IGNORE = "IGNORE";
    private static final String CONSOLELOGLEVEL = "consoleLogLevel";
    private static final String LOGFILE = "logFile";
    private static final String LOGGING = "logging";
    private static final String LOGLEVEL = "loggingLevel";
    public static final String FIRST = "first";
    public static final String standardHelp = "-----------\nBoolean (yes/no) parameters can be flipped from their defaults by specifying them as command line option\nPlease prefix numerical values starting with a '-' in braces with a '/'\nThe following options to the java vm may be useful:\n\tjava -Xmx[megabytes]m : max memory for the virtual machine\n\t''   -Xms[megabytes]m : min memory to the virtual machine\n\t''   -server : start the vm in server rather than client mode,\n\t     maybe faster, may have larger memory requirements \n\t     and slower start up ";
    public static final String basicNoInputString = "--help for more instructions";
    Map<Comparable, InputNode> nameToValues = new HashMap<Comparable, InputNode>();
    Map<Comparable, InputNode> tagsToValues = new HashMap<Comparable, InputNode>();
    String noInputString = "";
    String helpString = "";
    int offset = 65;

    public InputMunger() {
        this.addWatch_VariableTermsLength(FIRST, (byte)35, IGNORE);
    }

    public void addStandardWatches() {
        this.addWatch(LOGGING, 0, "Set logging");
        this.addWatch(LOGFILE, 1, "Set the log file (default = %t/bp.log)");
        this.addWatch(LOGLEVEL, 1, "Set the logging level");
        this.addWatch(CONSOLELOGLEVEL, 1, "Set the console log level");
    }

    public boolean addWatch(String string, byte by, int n, String string2) {
        if (this.nameToValues.containsKey(string)) {
            return false;
        }
        Byte by2 = new Byte(by);
        if (this.tagsToValues.containsKey(by2)) {
            return false;
        }
        InputNode inputNode = new InputNode();
        inputNode.numberOfTerms = n;
        this.nameToValues.put((Comparable)((Object)string), inputNode);
        this.tagsToValues.put(by2, inputNode);
        if (string2 != IGNORE) {
            inputNode.description = "\t-" + (char)by + "\t" + string2;
        }
        return true;
    }

    public boolean addWatch(String string, int n, String string2) {
        if (this.offset >= 128) {
            throw new RuntimeException("The maximum number of options have been specified");
        }
        if (this.addWatch(string, (byte)this.offset, n, string2)) {
            ++this.offset;
            if (this.offset == 91) {
                this.offset = 97;
            }
            return true;
        }
        return false;
    }

    public boolean addWatch_VariableTermsLength(String string, byte by, String string2) {
        return this.addWatch(string, by, Integer.MAX_VALUE, string2);
    }

    public boolean addWatch_VariableTermsLength(String string, String string2) {
        return this.addWatch(string, Integer.MAX_VALUE, string2);
    }

    public String help() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.helpString + "\n");
        stringBuffer.append("Arguments:\n");
        Object[] arrobject = this.tagsToValues.keySet().toArray(new Byte[0]);
        Arrays.sort(arrobject);
        for (Object object : arrobject) {
            String string = this.tagsToValues.get((Object)object).description;
            if (string == null) continue;
            stringBuffer.append(string + "\n");
        }
        stringBuffer.append(standardHelp);
        return stringBuffer.toString();
    }

    public void helpString(String string) {
        this.helpString = string;
    }

    public void noInputString(String string) {
        this.noInputString = string;
    }

    public boolean parseInput(String[] arrstring) {
        if (arrstring.length == 0) {
            Debug.pl(this.noInputString);
            Debug.pl(basicNoInputString);
            return false;
        }
        if (helpPattern.matcher(arrstring[0]).matches()) {
            Debug.pl(this.help());
            return false;
        }
        LinkedList<String> linkedList = new LinkedList<String>(Arrays.asList(arrstring));
        linkedList.add(0, "-#");
        arrstring = linkedList.toArray(new String[0]);
        for (int i = 0; i < arrstring.length; ++i) {
            if (arrstring[i].startsWith("-")) {
                byte[] arrby = arrstring[i].getBytes();
                for (int j = 1; j < arrby.length; ++j) {
                    Byte by = new Byte(arrby[j]);
                    if (!this.tagsToValues.containsKey(by)) {
                        throw new InputMungerException(" unrecognised input flag ");
                    }
                    InputNode inputNode = this.tagsToValues.get(by);
                    inputNode.seen = true;
                    if (inputNode.numberOfTerms != Integer.MAX_VALUE) {
                        inputNode.strings = new String[inputNode.numberOfTerms];
                        if (arrstring.length <= inputNode.numberOfTerms + i) {
                            throw new InputMungerException("Too few input terms for watch");
                        }
                        for (int k = 0; k < inputNode.numberOfTerms; ++k) {
                            String string = arrstring[i + 1 + k];
                            if (string.startsWith("-")) {
                                throw new InputMungerException("Too few input terms for watch");
                            }
                            inputNode.strings[k] = string;
                        }
                        i += inputNode.numberOfTerms;
                        continue;
                    }
                    ArrayList<String> arrayList = new ArrayList<String>();
                    while (i + 1 < arrstring.length && !arrstring[i + 1].startsWith("-")) {
                        arrayList.add(arrstring[++i]);
                    }
                    inputNode.strings = arrayList.toArray(new String[0]);
                }
                continue;
            }
            throw new InputMungerException("Input encountered without a supporting flag");
        }
        return true;
    }

    public double parseValue(double d, String string) {
        if (this.watchSet(string)) {
            String string2 = this.nameToValues.get((Object)string).strings[0];
            if (string2.startsWith("/")) {
                string2 = string2.substring(1);
            }
            double d2 = Double.parseDouble(string2);
            logger.info(string + " : " + d2);
            return d2;
        }
        logger.info(string + " : " + d);
        return d;
    }

    public int parseValue(int n, String string) {
        if (this.watchSet(string)) {
            String string2 = this.nameToValues.get((Object)string).strings[0];
            if (string2.startsWith("/")) {
                string2 = string2.substring(1);
            }
            int n2 = Integer.parseInt(string2);
            logger.info(string + " : " + n2);
            return n2;
        }
        logger.info(string + " : " + n);
        return n;
    }

    public String parseValue(String string, String string2) {
        if (this.watchSet(string2)) {
            String string3 = this.nameToValues.get((Object)string2).strings[0];
            logger.info(string2 + " : " + string3);
            return string3;
        }
        logger.info(string2 + " : " + string);
        return string;
    }

    public void processStandardWatches() throws IOException {
        Logger.getLogger("").setLevel(Level.OFF);
        if (this.watchSet(LOGGING)) {
            String string = this.parseValue("%tbp.log", LOGFILE);
            FileHandler fileHandler = new FileHandler(string);
            fileHandler.setFormatter(new SimpleFormatter());
            Level level = this.watchSet(LOGLEVEL) ? Level.parse(this.watchStrings(LOGLEVEL)[0]) : Level.INFO;
            Logger.getLogger("").setLevel(level);
            fileHandler.setLevel(level);
            if (this.watchSet(CONSOLELOGLEVEL)) {
                Logger.getLogger("").getHandlers()[0].setLevel(Level.parse(this.watchStrings(CONSOLELOGLEVEL)[0]));
            }
            Logger.getLogger("").addHandler(fileHandler);
        }
    }

    public void reset() {
        for (InputNode inputNode : this.nameToValues.values()) {
            inputNode.strings = null;
            inputNode.seen = false;
        }
    }

    public boolean watchSet(String string) {
        if (!this.nameToValues.containsKey(string)) {
            throw new InputMungerException("Watch does not exist");
        }
        boolean bl = this.nameToValues.get((Object)string).seen;
        logger.info(string + " has been set : " + bl);
        return bl;
    }

    public String[] watchStrings(String string) {
        Object[] arrobject = this.nameToValues.get((Object)string).strings;
        logger.info(string + " : " + IterationTools.join(arrobject, " "));
        return arrobject;
    }
    private static class InputNode {
        int numberOfTerms = 0;
        String description;
        boolean seen = false;
        String[] strings = null;

        private InputNode() {
        }
    }
    static class InputMungerException
    extends RuntimeException {
        private static final long serialVersionUID = -4456579905279386970L;

        public InputMungerException(String string) {
            super(string);
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.Generator;
import bp.common.io.FastaParser_Generator_Int;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class MultiFastaParser_Generator
implements Generator {
    private final FastaParser_Generator_Int[] fPGA;
    private final InputStream[] iSA;
    private boolean finished;
    private final String[] iDs;

    public MultiFastaParser_Generator(String string) throws IOException {
        int n;
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        InputStream inputStream = new BufferedInputStream(new FileInputStream(string));
        int n2 = 0;
        while ((n = inputStream.read()) != -1) {
            if (n == 62) {
                linkedList.add(new Integer(n2));
            }
            ++n2;
        }
        inputStream.close();
        n2 = 0;
        this.fPGA = new FastaParser_Generator_Int[linkedList.size()];
        this.iDs = new String[linkedList.size()];
        this.iSA = new InputStream[this.fPGA.length];
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            FastaParser_Generator_Int fastaParser_Generator_Int;
            n = (Integer)iterator.next();
            inputStream = new FileInputStream(string);
            inputStream.skip(n);
            this.iSA[n2] = inputStream = new BufferedInputStream(inputStream);
            this.fPGA[n2] = fastaParser_Generator_Int = new FastaParser_Generator_Int(inputStream, Integer.MAX_VALUE);
            this.iDs[n2++] = fastaParser_Generator_Int.getFastaID();
        }
        this.finished = this.fPGA.length == 0;
    }

    public Object gen() {
        if (this.finished) {
            return null;
        }
        int[] arrn = new int[this.fPGA.length];
        for (int i = 0; i < this.fPGA.length; ++i) {
            arrn[i] = this.fPGA[i].gen();
        }
        if (arrn[0] == Integer.MAX_VALUE) {
            this.finished = true;
            for (InputStream inputStream : this.iSA) {
                try {
                    inputStream.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    throw new IllegalStateException();
                }
            }
            return null;
        }
        return arrn;
    }

    public void close() throws IOException {
        for (InputStream inputStream : this.iSA) {
            inputStream.close();
        }
    }

    public String[] getFastaIDs() {
        return this.iDs;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.io;

import bp.common.fp.Generator;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewickTreeParser {
    Pattern illegalLabel = Pattern.compile("[\\(\\)\\:\\,\\;]+");
    String token;
    Generator tokenGen;
    public final Node tree;

    public static Generator tokenise(final Reader reader) {
        return new Generator(){
            StreamTokenizer sT;
            {
                this.sT = new StreamTokenizer(reader);
                this.sT.resetSyntax();
                this.sT.whitespaceChars(0, 32);
                this.sT.wordChars(33, 255);
                this.sT.ordinaryChar(40);
                this.sT.ordinaryChar(41);
                this.sT.ordinaryChar(91);
                this.sT.ordinaryChar(93);
                this.sT.ordinaryChar(123);
                this.sT.ordinaryChar(125);
                this.sT.ordinaryChar(44);
                this.sT.ordinaryChar(58);
                this.sT.ordinaryChar(59);
                this.sT.quoteChar(39);
                this.sT.quoteChar(34);
            }

            public Object gen() {
                int n;
                try {
                    n = this.sT.nextToken();
                }
                catch (IOException iOException) {
                    throw new IllegalStateException();
                }
                switch (n) {
                    case -1: {
                        return null;
                    }
                    case -3: {
                        return this.sT.sval;
                    }
                    case 34: 
                    case 39: {
                        return this.sT.sval;
                    }
                }
                return (char)n + "";
            }
        };
    }

    public static Generator commentEater(final Generator generator) {
        return new Generator(){

            public Object gen() {
                String string = (String)generator.gen();
                if (string != null) {
                    if (string.equals("[")) {
                        while (!(string = (String)generator.gen()).equals("]")) {
                            if (string != null) continue;
                            throw new IllegalArgumentException();
                        }
                        return this.gen();
                    }
                    return string;
                }
                return string;
            }
        };
    }

    public NewickTreeParser(Generator generator) {
        this.tokenGen = generator;
        this.token = (String)generator.gen();
        this.tree = this.parseTree();
    }

    void nextToken() {
        this.token = (String)this.tokenGen.gen();
    }

    void parseBranchLength(Node node) {
        if (this.token.equals(":")) {
            this.nextToken();
            node.edgeLength = Double.parseDouble(this.token);
            this.nextToken();
        }
    }

    Node parseDescendant_List() {
        Node node = new Node();
        if (!this.token.equals("(")) {
            throw new IllegalArgumentException();
        }
        this.nextToken();
        do {
            node.addNode(this.parseSubTree());
            if (!this.token.equals(",")) break;
            this.nextToken();
        } while (true);
        if (!this.token.equals(")")) {
            throw new IllegalArgumentException(this.token);
        }
        this.nextToken();
        return node;
    }

    void parseRootLabel(Node node) {
        this.parseLabel(node);
    }

    void parseInternalNodeLabel(Node node) {
        this.parseLabel(node);
    }

    void parseLabel(Node node) {
        if (!this.illegalLabel.matcher(this.token).find()) {
            node.o = this.token;
            this.nextToken();
        } else {
            node.o = "";
        }
    }

    Node parseLeafLabel() {
        Node node = new Node();
        this.parseLabel(node);
        return node;
    }

    Node parseSubTree() {
        if (this.token.equals("(")) {
            Node node = this.parseDescendant_List();
            this.parseInternalNodeLabel(node);
            this.parseBranchLength(node);
            return node;
        }
        Node node = this.parseLeafLabel();
        this.parseBranchLength(node);
        return node;
    }

    Node parseTree() {
        Node node = this.parseDescendant_List();
        this.parseInternalNodeLabel(node);
        this.parseBranchLength(node);
        if (!this.token.equals(";")) {
            throw new IllegalArgumentException();
        }
        return node;
    }
    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Node {
        private static final Pattern noQuotesNeeded = Pattern.compile("[a-zA-Z0-9]*");
        public double edgeLength = Double.MIN_VALUE;
        private Node parent;
        private final LinkedList<Object> nodes = new LinkedList();
        public Object o = null;

        public Node(Node node) {
            node.addNode(this);
        }

        public Node() {
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.nodes.size() != 0) {
                stringBuffer.append("(");
                int n = 0;
                stringBuffer.append(this.nodes.get(n).toString());
                while (++n < this.nodes.size()) {
                    stringBuffer.append(",");
                    stringBuffer.append(this.nodes.get(n).toString());
                }
                stringBuffer.append(")");
            }
            if (this.o != null) {
                stringBuffer.append(noQuotesNeeded.matcher(this.o.toString()).matches() ? this.o.toString() : "'" + this.o.toString() + "'");
            }
            if (this.edgeLength != Double.MIN_VALUE) {
                stringBuffer.append(":" + this.edgeLength);
            }
            if (this.parent == null) {
                stringBuffer.append(";");
            }
            return stringBuffer.toString();
        }

        public Node getParent() {
            return this.parent;
        }

        public List<Object> getNodes() {
            return (List)this.nodes.clone();
        }

        public void addNode(Node node) {
            this.nodes.add(node);
            if (node.parent != null && node.parent != this) {
                throw new IllegalStateException();
            }
            node.parent = this;
        }

        public void removeNode(int n) {
            Node node = (Node)this.nodes.remove(n);
            node.parent = null;
        }

        public Node setNode(int n, Node node) {
            Node node2 = (Node)this.nodes.set(n, node);
            node2.parent = null;
            if (node.parent != null && node.parent != this) {
                throw new IllegalStateException();
            }
            node.parent = this;
            return node2;
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.common.maths;

import bp.common.fp.Function_Int;

public final class Maths {
    public static final float LOG_UNDERFLOW_THRESHOLD = 7.5f;
    public static final float LOG_ZERO = -2.0E20f;

    public static Function_Int bitCounter() {
        return new Function_Int(){
            final int mask;
            int[] table = new int[(int)Math.pow(2.0, 16.0)];
            {
                for (int i = 0; i < this.table.length; ++i) {
                    int n = 0;
                    for (int j = i; j != 0; j >>= 1) {
                        if ((j & 1) == 0) continue;
                        ++n;
                    }
                    this.table[i] = n;
                }
                this.mask = (int)Math.pow(2.0, 16.0) - 1;
            }

            public int fn(int n) {
                return this.table[n & this.mask] + this.table[n >>> 16];
            }
        };
    }

    public static final float exp(float f) {
        if (f > -2.0f) {
            if ((double)f > -0.5) {
                if (f > 0.0f) {
                    return (float)Math.exp(f);
                }
                return (((0.03254409f * f + 0.16280432f) * f + 0.49929762f) * f + 0.9999515f) * f + 0.9999993f;
            }
            if (f > -1.0f) {
                return (((0.01973899f * f + 0.1382238f) * f + 0.4805665f) * f + 0.9932694f) * f + 0.99906754f;
            }
            return (((0.009405282f * f + 0.094149634f) * f + 0.40825793f) * f + 0.93933624f) * f + 0.9836951f;
        }
        if (f > -8.0f) {
            if (f > -4.0f) {
                return (((0.002172457f * f + 0.034848295f) * f + 0.221182f) * f + 0.6704946f) * f + 0.8355695f;
            }
            return (((1.2398772E-4f * f + 0.003491558f) * f + 0.037277214f) * f + 0.17974998f) * f + 0.332493f;
        }
        if (f > -16.0f) {
            return (((5.174171E-7f * f + 2.7214568E-5f) * f + 5.34186E-4f) * f + 0.0046410197f) * f + 0.015074479f;
        }
        return 0.0f;
    }

    public static final double log(double d) {
        return Math.log(d);
    }

    public static final float log(float f) {
        return (float)Maths.log((double)f);
    }

    public static final double logAddQuality(double d, double d2) {
        if (d < d2) {
            return d == Double.NEGATIVE_INFINITY ? d2 : Math.log(Math.exp(d - d2) + 1.0) + d2;
        }
        return d2 == Double.NEGATIVE_INFINITY ? d : Math.log(Math.exp(d2 - d) + 1.0) + d;
    }

    public static final float logAdd(float f, float f2) {
        if (f < f2) {
            return f <= -2.0E20f || f2 - f >= 7.5f ? f2 : Maths.lookup(f2 - f) + f;
        }
        return f2 <= -2.0E20f || f - f2 >= 7.5f ? f : Maths.lookup(f - f2) + f2;
    }

    public static final float lookup(float f) {
        if (f <= 2.5f) {
            if (f <= 1.0f) {
                return ((-0.0093508335f * f + 0.13065952f) * f + 0.4987998f) * f + 0.6932031f;
            }
            return ((-0.014532322f * f + 0.13994232f) * f + 0.4956355f) * f + 0.6921406f;
        }
        if (f <= 4.5f) {
            return ((-0.0046050316f * f + 0.06342742f) * f + 0.69595647f) * f + 0.51427263f;
        }
        return ((-4.586616E-4f * f + 0.009695946f) * f + 0.9307347f) * f + 0.16803716f;
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.fp.Function_Int_2Args;
import bp.common.fp.Function_Int_3Args;
import bp.common.fp.Generator;
import bp.common.fp.Predicate_Double_2Args;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_2Args;
import bp.common.fp.Procedure_Int_3Args;
import bp.common.fp.Procedures;
import bp.common.io.NewickTreeParser;
import bp.pecan.AlignmentStitcher;
import bp.pecan.DripAligner;
import bp.pecan.Librarian;
import bp.pecan.WeightTranslator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AlignmentPump {
    static final int[] getStartAndEnds(int n, int[][] arrn, int n2) {
        int[] arrn2 = new int[n * 2];
        for (int i = 0; i < n; ++i) {
            int[] arrn3 = arrn[n2 + i];
            arrn2[i * 2] = arrn3[0];
            arrn2[i * 2 + 1] = arrn3[1];
        }
        return arrn2;
    }

    static final int[] getRange(int n, int n2) {
        int[] arrn = new int[n];
        for (int i = 0; i < n; ++i) {
            arrn[i] = i + n2;
        }
        return arrn;
    }

    static final int getNodeNumber(NewickTreeParser.Node node) {
        if (node.getNodes().size() == 0) {
            return 1;
        }
        int n = 0;
        Iterator<Object> iterator = node.getNodes().iterator();
        while (iterator.hasNext()) {
            n += AlignmentPump.getNodeNumber((NewickTreeParser.Node)iterator.next());
        }
        return n;
    }

    public static final int getAlignmentPumps(Procedure procedure, Librarian.WeightsGetter weightsGetter, Function_Int_3Args function_Int_3Args, int[][] arrn, Procedure[] arrprocedure, int n, NewickTreeParser.Node node, int n2, Function_Int_2Args function_Int_2Args, DripAligner.Add add, Predicate_Double_2Args predicate_Double_2Args, double d, Procedure_Int_3Args procedure_Int_3Args, int[] arrn2) {
        if (node.getNodes().size() == 0) {
            arrprocedure[n++] = procedure;
        } else {
            LinkedList<Object> linkedList = new LinkedList<Object>();
            Iterator<Object> iterator = node.getNodes().iterator();
            while (iterator.hasNext()) {
                linkedList.add(iterator.next());
            }
            node = (NewickTreeParser.Node)linkedList.remove(0);
            int n3 = AlignmentPump.getNodeNumber(node);
            int[] arrn3 = AlignmentPump.getStartAndEnds(n3, arrn, n);
            int[] arrn4 = AlignmentPump.getRange(n3, n);
            while (linkedList.size() > 0) {
                n3 = 0;
                Iterator iterator2 = linkedList.iterator();
                while (iterator2.hasNext()) {
                    n3 += AlignmentPump.getNodeNumber((NewickTreeParser.Node)iterator2.next());
                }
                int n4 = n + arrn4.length;
                int[] arrn5 = AlignmentPump.getStartAndEnds(n3, arrn, n4);
                int[] arrn6 = AlignmentPump.getRange(n3, n4);
                Procedure[] arrprocedure2 = AlignmentPump.getAlignmentPump(arrn4, arrn6, arrn3, arrn5, procedure, weightsGetter, function_Int_3Args, n2, function_Int_2Args, add, predicate_Double_2Args, d, procedure_Int_3Args, arrn2);
                n = AlignmentPump.getAlignmentPumps(arrprocedure2[0], weightsGetter, function_Int_3Args, arrn, arrprocedure, n, node, n2, function_Int_2Args, add, predicate_Double_2Args, d, procedure_Int_3Args, arrn2);
                procedure = arrprocedure2[1];
                node = (NewickTreeParser.Node)linkedList.remove(0);
                n3 = AlignmentPump.getNodeNumber(node);
                arrn3 = AlignmentPump.getStartAndEnds(n3, arrn, n);
                arrn4 = AlignmentPump.getRange(n3, n);
            }
            n = AlignmentPump.getAlignmentPumps(procedure, weightsGetter, function_Int_3Args, arrn, arrprocedure, n, node, n2, function_Int_2Args, add, predicate_Double_2Args, d, procedure_Int_3Args, arrn2);
        }
        return n;
    }

    static final Procedure[] getAlignmentPump(int[] arrn, int[] arrn2, int[] arrn3, int[] arrn4, final Procedure procedure, Librarian.WeightsGetter weightsGetter, Function_Int_3Args function_Int_3Args, int n, Function_Int_2Args function_Int_2Args, DripAligner.Add add, Predicate_Double_2Args predicate_Double_2Args, double d, Procedure_Int_3Args procedure_Int_3Args, int[] arrn5) {
        WeightTranslator weightTranslator = new WeightTranslator(weightsGetter, function_Int_3Args, arrn, arrn2, arrn3, arrn4, n, function_Int_2Args, AlignmentStitcher.convertInput(), procedure_Int_3Args, arrn5);
        DripAligner dripAligner = new DripAligner(weightTranslator.getOutputX(), weightTranslator.getOutputY(), add, predicate_Double_2Args, d);
        final AlignmentStitcher alignmentStitcher = new AlignmentStitcher(dripAligner, arrn3.length / 2, arrn4.length / 2);
        Procedure procedure2 = new Procedure(){

            public final void pro(Object object) {
                object = alignmentStitcher.gen();
                if (object != null) {
                    procedure.pro(object);
                }
            }
        };
        return new Procedure[]{Procedures.runProcedures(new Procedure[]{alignmentStitcher.getInputX(), weightTranslator.getInputX(), procedure2}), Procedures.runProcedures(new Procedure[]{alignmentStitcher.getInputY(), weightTranslator.getInputY(), procedure2})};
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.fp.Generator;
import bp.common.fp.IterationTools;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_2Args;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class AlignmentStitcher
implements Generator {
    Generator alignmentGenerator;
    Procedure rowProcedure;
    Procedure columnProcedure;
    final List row;
    final List column;
    final int[] rowGap;
    final int[] columnGap;

    public AlignmentStitcher(Generator generator, int n, int n2) {
        this.alignmentGenerator = generator;
        this.row = new LinkedList();
        this.column = new LinkedList();
        this.rowGap = new int[n];
        Arrays.fill(this.rowGap, Integer.MAX_VALUE);
        this.columnGap = new int[n2];
        Arrays.fill(this.columnGap, Integer.MAX_VALUE);
        this.rowProcedure = new Procedure(){

            public final void pro(Object object) {
                AlignmentStitcher.this.row.addAll((List)object);
            }
        };
        this.columnProcedure = new Procedure(){

            public final void pro(Object object) {
                AlignmentStitcher.this.column.addAll((List)object);
            }
        };
    }

    public Procedure getInputX() {
        return this.rowProcedure;
    }

    public Procedure getInputY() {
        return this.columnProcedure;
    }

    public final Object gen() {
        Generator generator = (Generator)this.alignmentGenerator.gen();
        if (generator != null) {
            Float f;
            LinkedList<Object[]> linkedList = new LinkedList<Object[]>();
            while ((f = (Float)generator.gen()) != null) {
                float f2 = f.floatValue();
                if (f2 == Float.POSITIVE_INFINITY) {
                    linkedList.add(new Object[]{this.row.remove(0), this.columnGap});
                    continue;
                }
                if (f2 == Float.NEGATIVE_INFINITY) {
                    linkedList.add(new Object[]{this.rowGap, this.column.remove(0)});
                    continue;
                }
                linkedList.add(new Object[]{this.row.remove(0), this.column.remove(0)});
            }
            return linkedList;
        }
        return null;
    }

    public static final Procedure_2Args convertInput() {
        return new Procedure_2Args(){

            public final void pro(Object object, Object object2) {
                int n = this.getCos(object, (int[])object2, 0);
                if (n != ((int[])object2).length) {
                    throw new IllegalArgumentException(n + " " + ((int[])object2).length + "  ( " + IterationTools.join((int[])object2, " ") + " ) ");
                }
            }

            final int getCos(Object object, int[] arrn, int n) {
                if (object instanceof Object[]) {
                    Object[] arrobject = (Object[])object;
                    n = this.getCos(arrobject[0], arrn, n);
                    return this.getCos(arrobject[1], arrn, n);
                }
                if (object instanceof int[]) {
                    int[] arrn2 = (int[])object;
                    System.arraycopy(arrn2, 0, arrn, n, arrn2.length);
                    return n + arrn2.length;
                }
                return n;
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.fp.Generator;
import bp.pecan.PolygonFiller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class AnchorParser_Generator {
    public static final int writeOutEdgeList(Generator generator, OutputStream outputStream) throws IOException {
        PolygonFiller.Node node;
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        int n = 0;
        while ((node = (PolygonFiller.Node)generator.gen()) != null) {
            dataOutputStream.writeInt(node.x);
            dataOutputStream.writeInt(node.y);
            dataOutputStream.writeInt(node.yMax);
            dataOutputStream.writeInt(node.z);
            ++n;
        }
        dataOutputStream.flush();
        return n;
    }

    public static final Generator readInEdgeList(final InputStream inputStream, final int n) {
        return new Generator(){
            DataInputStream dIS;
            int j;
            {
                this.dIS = new DataInputStream(inputStream);
                this.j = n;
            }

            public final Object gen() {
                if (this.j-- > 0) {
                    try {
                        return new PolygonFiller.Node(this.dIS.readInt(), this.dIS.readInt(), this.dIS.readInt(), this.dIS.readInt());
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                        throw new IllegalStateException();
                    }
                }
                return null;
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.Array;
import bp.common.fp.Function_Int;
import bp.common.io.NewickTreeParser;
import bp.common.maths.Maths;
import bp.pecan.Pecan;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Cell {
    static final Logger logger = Logger.getLogger(Pecan.class.getName());
    public static final int PROGRAM = 0;
    public static final int TRANSITIONS = 1;
    public static final int EMISSIONS = 2;
    public static final int STARTSTATES = 3;
    public static final int ENDSTATES = 4;
    public static final int TRANSLATEALPHABETCHAR = 5;
    public static final int ALPHABETSIZE = 6;
    public static final int EMISSION_TYPES = 7;

    static final void checkAlphabet(NewickTreeParser.Node node) {
        if (node.getNodes().size() == 0) {
            throw new IllegalStateException();
        }
        for (int i = 0; i < node.getNodes().size(); ++i) {
            Cell.checkCharacter((NewickTreeParser.Node)node.getNodes().get(i));
        }
    }

    static final void checkBeginProbs(NewickTreeParser.Node node, int n) {
        Cell.checkProbs(node, n);
    }

    static final void checkCharacter(NewickTreeParser.Node node) {
        String string = (String)node.o;
        if (string.length() == 0) {
            throw new IllegalArgumentException();
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        for (int i = 0; i < string.length(); ++i) {
            if (pattern.matcher("" + string.charAt(i)).matches()) continue;
            throw new IllegalArgumentException();
        }
    }

    static final float checkConnection(NewickTreeParser.Node node, int n, float f) {
        float[] arrf = Cell.parseFloats(Cell.getStrings(node));
        if (!(arrf[0] >= 0.0f) || !(arrf[0] < (float)n)) {
            throw new IllegalArgumentException();
        }
        f = (float)Maths.logAddQuality(f, arrf[1]);
        return f;
    }

    static final void checkEmission(NewickTreeParser.Node node, int n, int n2) {
        int n3 = (int)Float.parseFloat((String)((NewickTreeParser.Node)node.getNodes().get((int)(node.getNodes().size() - 2))).o);
        if (n3 == 0) {
            Cell.checkForConnectionsToProceedingStates(node, n2);
        }
        if (n3 < 0 || n3 >= 4) {
            throw new IllegalArgumentException();
        }
        Cell.checkProbs((NewickTreeParser.Node)node.getNodes().get(node.getNodes().size() - 1), Integer.MAX_VALUE);
    }

    static final void checkEndProbs(NewickTreeParser.Node node, int n) {
        Cell.checkProbs(node, n);
    }

    static final void checkForConnectionsToProceedingStates(NewickTreeParser.Node node, int n) {
        for (int i = 0; i < node.getNodes().size() - 2; ++i) {
            float[] arrf = Cell.parseFloats(Cell.getStrings((NewickTreeParser.Node)node.getNodes().get(i)));
            if (!(arrf[0] >= (float)n)) continue;
            throw new IllegalArgumentException();
        }
    }

    static final void checkProbability(float f) {
        if (!((double)(f = (float)Math.exp(f)) < 1.000001) || !((double)f > 0.999999)) {
            throw new IllegalArgumentException(" " + f);
        }
    }

    static final void checkProbs(NewickTreeParser.Node node, int n) {
        float[] arrf = Cell.parseFloats(Cell.getStrings(node));
        float f = Float.NEGATIVE_INFINITY;
        for (float f2 : arrf) {
            f = (float)Maths.logAddQuality(f2, f);
        }
        if (n != Integer.MAX_VALUE && arrf.length != n) {
            throw new IllegalArgumentException(n + " " + arrf.length);
        }
        Cell.checkProbability(f);
    }

    static final void checkState(NewickTreeParser.Node node, int n, int n2, int n3) {
        float f = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < node.getNodes().size() - 2; ++i) {
            f = Cell.checkConnection((NewickTreeParser.Node)node.getNodes().get(i), n, f);
        }
        Cell.checkProbability(f);
        Cell.checkEmission(node, n2, n3);
    }

    public static final Object[] createProgram(NewickTreeParser.Node node, int n, int n2) {
        int n3 = ((NewickTreeParser.Node)node.getNodes().get(0)).getNodes().size();
        int[] arrn = Cell.translateAlphabetChar(Cell.getStrings((NewickTreeParser.Node)node.getNodes().get(0)), n2);
        float[] arrf = Cell.parseFloats(Cell.getStrings((NewickTreeParser.Node)node.getNodes().get(1)));
        float[] arrf2 = Cell.parseFloats(Cell.getStrings((NewickTreeParser.Node)node.getNodes().get(2)));
        int n4 = node.getNodes().size() - 3;
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        LinkedList<LinkedList> linkedList2 = new LinkedList<LinkedList>();
        for (int i = 0; i < n4; ++i) {
            linkedList2.add(new LinkedList());
        }
        LinkedList linkedList3 = new LinkedList();
        for (int i = 0; i < n4; ++i) {
            Cell.parseState(i, (NewickTreeParser.Node)node.getNodes().get(i + 3), linkedList3, linkedList, linkedList2, n3 * n3);
        }
        LinkedList<Integer> linkedList4 = new LinkedList<Integer>();
        LinkedList<Float> linkedList5 = new LinkedList<Float>();
        linkedList4.add(new Integer(n));
        linkedList5.add(new Float(Float.NaN));
        for (int i = 0; i < n4; ++i) {
            List list = (List)linkedList2.get(i);
            int n5 = (Integer)linkedList.get(i);
            for (float[] arrf3 : list) {
                linkedList4.add(new Integer(n5 * n4 + (int)arrf3[0]));
                linkedList5.add(new Float(arrf3[1]));
            }
            linkedList4.add(new Integer(n));
            linkedList5.add(new Float(Float.NaN));
        }
        return new Object[]{Cell.listToIntArray(linkedList4), Cell.listToFloatArray(linkedList5), Cell.concatenateFloatArrays(linkedList3), arrf, arrf2, Array.matrix1DLookUp(arrn), new Integer(n3), Cell.listToIntArray(linkedList)};
    }

    static final String[] getStrings(NewickTreeParser.Node node) {
        String[] arrstring = new String[node.getNodes().size()];
        for (int i = 0; i < arrstring.length; ++i) {
            arrstring[i] = (String)((NewickTreeParser.Node)node.getNodes().get((int)i)).o;
        }
        return arrstring;
    }

    public static final void isLegitimateHMM(NewickTreeParser.Node node) {
        Cell.checkAlphabet((NewickTreeParser.Node)node.getNodes().get(0));
        int n = ((NewickTreeParser.Node)node.getNodes().get(0)).getNodes().size();
        int n2 = node.getNodes().size() - 3;
        Cell.checkBeginProbs((NewickTreeParser.Node)node.getNodes().get(1), n2);
        Cell.checkEndProbs((NewickTreeParser.Node)node.getNodes().get(2), n2);
        for (int i = 0; i < n2; ++i) {
            Cell.checkState((NewickTreeParser.Node)node.getNodes().get(i + 3), n2, n, i);
        }
    }

    static final int[] listToIntArray(List<Integer> list) {
        int[] arrn = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            arrn[i] = list.get(i);
        }
        return arrn;
    }

    static final float[] listToFloatArray(List<Float> list) {
        float[] arrf = new float[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            arrf[i] = list.get(i).floatValue();
        }
        return arrf;
    }

    static final float[] concatenateFloatArrays(List list) {
        int n = 0;
        float[] arrf = list.iterator();
        while (arrf.hasNext()) {
            n += ((float[])arrf.next()).length;
        }
        arrf = new float[n];
        n = 0;
        for (float[] arrf2 : list) {
            System.arraycopy(arrf2, 0, arrf, n, arrf2.length);
            n += arrf2.length;
        }
        return arrf;
    }

    public static final void transformProgram(int[] arrn, int n, int n2) {
        block6: for (int i = 0; i < arrn.length; ++i) {
            if (arrn[i] == Integer.MAX_VALUE) continue;
            switch (arrn[i] / n2) {
                case 0: {
                    continue block6;
                }
                case 1: {
                    arrn[i] = -n2 + arrn[i] % n2;
                    continue block6;
                }
                case 2: {
                    arrn[i] = -n2 * (n + 1) + arrn[i] % n2;
                    continue block6;
                }
                case 3: {
                    arrn[i] = -n2 * n + arrn[i] % n2;
                    continue block6;
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
    }

    public static GetCellCalculator getForwardCellCalculator(final int[] arrn, final float[] arrf, final float[] arrf2, final int n) {
        return new GetCellCalculator(){

            public CellCalculator getCellCalculator(Function_Int function_Int, Function_Int function_Int2) {
                return Cell.nextSumForward(arrn, arrf, arrf2, n, function_Int, function_Int2);
            }
        };
    }

    public static final CellCalculator nextSumForward(final int[] arrn, final float[] arrf, final float[] arrf2, final int n, final Function_Int function_Int, final Function_Int function_Int2) {
        return new CellCalculator(){
            private final int jump;
            {
                this.jump = n * n;
            }

            public final void calc(float[] arrf3, int n4, int n2, int n3) {
                Cell.nextSumForward(arrf3, arrn, arrf, arrf2, this.jump, function_Int.fn(n2) * n + function_Int2.fn(n3), n4);
            }
        };
    }

    public static final void nextSumForward(float[] arrf, int[] arrn, float[] arrf2, float[] arrf3, int n, int n2, int n3) {
        int n4 = 1;
        int n5 = 0;
        while (n4 < arrn.length) {
            float f = arrf[arrn[n4] + n3] + arrf2[n4];
            ++n4;
            while (arrn[n4] != Integer.MAX_VALUE) {
                f = Maths.logAdd(f, arrf[arrn[n4] + n3] + arrf2[n4]);
                ++n4;
            }
            ++n4;
            arrf[n5++ + n3] = f + arrf3[n2];
            n2 += n;
        }
    }

    public static final Object[] makeRProgram(int[] arrn, float[] arrf, int n) {
        int[] arrn2 = new int[arrn.length + n];
        float[] arrf2 = new float[arrn2.length];
        int n2 = 0;
        int n3 = 0;
        int n4 = n - 1;
        while (n2 < arrn.length - 1) {
            if (arrn[n2] == Integer.MAX_VALUE) {
                arrf2[n3] = Float.NaN;
                arrn2[n3++] = Integer.MAX_VALUE;
                arrf2[n3] = Float.NaN;
                arrn2[n3++] = arrn[n2] - (arrn[++n2] < 0 ? n - 1 - Math.abs((arrn[n2] + 1) % n) : arrn[n2] % n) + n4--;
                continue;
            }
            arrf2[n3] = arrf[n2];
            arrn2[n3++] = arrn[n2] < 0 ? Math.abs((arrn[n2] + 1) % n) : n - 1 - arrn[n2] % n;
            ++n2;
        }
        arrf2[n3] = Float.NaN;
        arrn2[n3] = Integer.MAX_VALUE;
        return new Object[]{arrn2, arrf2};
    }

    public static GetCellCalculator getBackwardCellCalculator(final int[] arrn, final float[] arrf, final float[] arrf2, final int n, final int n2) {
        return new GetCellCalculator(){

            public CellCalculator getCellCalculator(Function_Int function_Int, Function_Int function_Int2) {
                return Cell.nextSumBackward(arrn, arrf, arrf2, n, n2, function_Int, function_Int2);
            }
        };
    }

    public static final CellCalculator nextSumBackward(final int[] arrn, final float[] arrf, final float[] arrf2, final int n, final int n2, final Function_Int function_Int, final Function_Int function_Int2) {
        return new CellCalculator(){
            private final int jump;
            {
                this.jump = n2 * n2;
            }

            public final void calc(float[] arrf3, int n4, int n22, int n3) {
                Cell.nextSumBackward(arrf3, arrn, arrf, arrf2, this.jump, function_Int.fn(n22 + 1) * n2 + function_Int2.fn(n3 + 1), n4, n);
            }
        };
    }

    public static final void nextSumBackward(float[] arrf, int[] arrn, float[] arrf2, float[] arrf3, int n, int n2, int n3, int n4) {
        Arrays.fill(arrf, n3, n3 + n4, Float.NEGATIVE_INFINITY);
        for (int i = 1; i < arrn.length; ++i) {
            float f = arrf[arrn[i] + n3] + arrf3[n2];
            n2 += n;
            arrf[arrn[++i] + n3] = Maths.logAdd(arrf[arrn[i] + n3], f + arrf2[i]);
            ++i;
            while (arrn[i] != Integer.MAX_VALUE) {
                arrf[arrn[i] + n3] = Maths.logAdd(arrf[arrn[i] + n3], f + arrf2[i]);
                ++i;
            }
        }
    }

    static final void parseConnection(int n, NewickTreeParser.Node node, List<LinkedList> list) {
        float[] arrf = Cell.parseFloats(Cell.getStrings(node));
        int n2 = (int)arrf[0];
        float f = arrf[1];
        float[] arrf2 = new float[]{n, f};
        list.get(n2).add(arrf2);
    }

    static final float[] parseFloats(String[] arrstring) {
        float[] arrf = new float[arrstring.length];
        for (int i = 0; i < arrf.length; ++i) {
            arrf[i] = Float.parseFloat(arrstring[i]);
        }
        return arrf;
    }

    static final void parseState(int n, NewickTreeParser.Node node, List list, List<Integer> list2, List<LinkedList> list3, int n2) {
        int n3 = node.getNodes().size() - 2;
        int n4 = (int)Float.parseFloat((String)((NewickTreeParser.Node)node.getNodes().get((int)n3)).o);
        list2.add(new Integer(n4));
        list.add(Cell.resizeArray(Cell.parseFloats(Cell.getStrings((NewickTreeParser.Node)node.getNodes().get(n3 + 1))), n2, n4));
        for (int i = 0; i < n3; ++i) {
            Cell.parseConnection(n, (NewickTreeParser.Node)node.getNodes().get(i), list3);
        }
    }

    static final float[] resizeArray(float[] arrf, int n, int n2) {
        int n3;
        float[] arrf2 = new float[n];
        if (n2 == 0 || n2 == 3) {
            for (n3 = 0; n3 < arrf2.length; n3 += arrf.length) {
                System.arraycopy(arrf, 0, arrf2, n3, arrf.length);
            }
        }
        if (n2 == 2) {
            System.arraycopy(arrf, 0, arrf2, 0, arrf.length);
        }
        if (n2 == 1) {
            for (n3 = 0; n3 < arrf.length; ++n3) {
                for (int i = 0; i < arrf.length; ++i) {
                    arrf2[n3 * arrf.length + i] = arrf[n3];
                }
            }
        }
        return arrf2;
    }

    public static final int[] translateAlphabetChar(String[] arrstring, int n) {
        int[] arrn = new int[256];
        Arrays.fill(arrn, n);
        for (int i = 0; i < arrstring.length; ++i) {
            String string = arrstring[i];
            for (int j = 0; j < string.length(); ++j) {
                arrn[string.charAt((int)j)] = i;
            }
        }
        return arrn;
    }
    public static final class FiveCell
    extends BasicCell {
        static final int MATCH_STATE = 0;
        static final int LEFT_GAP_STATE = 1;
        static final int RIGHT_GAP_STATE = 2;
        static final int LEFT_JUNK_STATE = 3;
        static final int RIGHT_JUNK_STATE = 4;
        static final int INVERSE_MATCH_STATE = 4;
        static final int INVERSE_LEFT_GAP_STATE = 3;
        static final int INVERSE_RIGHT_GAP_STATE = 2;
        static final int INVERSE_LEFT_JUNK_STATE = 1;
        static final int INVERSE_RIGHT_JUNK_STATE = 0;
        static final int N_EMISSION = 4;
        static final float MATCH = 0.97038335f;
        static final float GAP_OPEN = 0.012986835f;
        static final float GAP_EXTEND = 0.71260625f;
        static final float GAP_SWITCH = 0.0073673674f;
        static final float JUNK_EXTEND = 0.99656343f;
        static final float MATCHL = Maths.log(0.97038335f);
        static final float GAP_OPENL = Maths.log(0.012986835f);
        static final float JUNK_OPENL = Maths.log(0.0018214881f);
        static final float GAP_EXTENDL = Maths.log(0.71260625f);
        static final float GAP_SWITCHL = Maths.log(0.0073673674f);
        static final float GAP_CLOSEL = Maths.log(0.28002638f);
        static final float JUNK_EXTENDL = Maths.log(0.99656343f);
        static final float JUNK_CLOSEL = Maths.log(0.0034365654f);
        static final float MATCH_EMISSION_N_L = Maths.log(0.04f);
        static final float MATCH_EMISSIONL = Maths.log(0.12064298f);
        static final float TRANSVERSION_EMISSIONL = Maths.log(0.0103672715f);
        static final float TRANSITION_EMISSIONL = Maths.log(0.018622477f);
        static final float GAP_EMISSIONL = Maths.log(0.2f);
        static final float[] emit = new float[]{MATCH_EMISSIONL, TRANSVERSION_EMISSIONL, TRANSITION_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSION_N_L, TRANSVERSION_EMISSIONL, MATCH_EMISSIONL, TRANSVERSION_EMISSIONL, TRANSITION_EMISSIONL, MATCH_EMISSION_N_L, TRANSITION_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSION_N_L, TRANSVERSION_EMISSIONL, TRANSITION_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSIONL, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L};

        public FiveCell() {
            this.translateChars = Array.matrix1DLookUp(Cell.translateAlphabetChar(new String[]{"aA", "cC", "gG", "tT", "nNxX"}, 4));
            this.startStates = new float[]{Maths.log(0.2f), Maths.log(0.2f), Maths.log(0.2f), Maths.log(0.2f), Maths.log(0.2f)};
            this.endStates = this.startStates;
            this.stateNumber = 5;
            this.alphabetSize = 5;
        }

        public final CellCalculator backwards(final int n, final int n2, final int n3, final Function_Int function_Int, final Function_Int function_Int2) {
            return new CellCalculator(){

                public final void calc(float[] arrf, int n4, int n22, int n32) {
                    n22 = function_Int.fn(n22 + 1);
                    n32 = function_Int2.fn(n32 + 1);
                    float f = emit[n22 * 5 + n32];
                    float f2 = Maths.logAdd(arrf[n + n4 + 3], arrf[n3 + n4 + 2]) + GAP_OPENL + GAP_EMISSIONL;
                    float f3 = Maths.logAdd(arrf[n + n4 + 1], arrf[n3 + n4 + 0]) + JUNK_OPENL + GAP_EMISSIONL;
                    f2 = Maths.logAdd(f2, f3);
                    arrf[4 + n4] = Maths.logAdd(arrf[n2 + n4 + 4] + MATCHL + f, f2);
                    float f4 = arrf[n2 + n4 + 4] + f;
                    f2 = Maths.logAdd(arrf[n + n4 + 3] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n3 + n4 + 2] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[3 + n4] = Maths.logAdd(f4 + GAP_CLOSEL, f2);
                    f2 = Maths.logAdd(arrf[n3 + n4 + 2] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n + n4 + 3] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[2 + n4] = Maths.logAdd(f4 + GAP_CLOSEL, f2);
                    arrf[1 + n4] = Maths.logAdd(arrf[n + n4 + 1] + JUNK_EXTENDL + GAP_EMISSIONL, f4 + JUNK_CLOSEL);
                    arrf[0 + n4] = Maths.logAdd(arrf[n3 + n4 + 0] + JUNK_EXTENDL + GAP_EMISSIONL, f4 + JUNK_CLOSEL);
                }
            };
        }

        public final CellCalculator forwards(final int n, final int n2, final int n3, final Function_Int function_Int, final Function_Int function_Int2) {
            return new CellCalculator(){

                public final void calc(float[] arrf, int n4, int n22, int n32) {
                    n22 = function_Int.fn(n22);
                    n32 = function_Int2.fn(n32);
                    float f = emit[n22 * 5 + n32];
                    float f2 = Maths.logAdd(arrf[n2 + n4 + 1], arrf[n2 + n4 + 2]) + GAP_CLOSEL;
                    float f3 = Maths.logAdd(arrf[n2 + n4 + 3], arrf[n2 + n4 + 4]) + JUNK_CLOSEL;
                    f2 = Maths.logAdd(f2, f3);
                    arrf[0 + n4] = Maths.logAdd(arrf[n2 + n4 + 0] + MATCHL, f2) + f;
                    f2 = Maths.logAdd(arrf[n + n4 + 1] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n + n4 + 2] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[1 + n4] = Maths.logAdd(arrf[n + n4 + 0] + GAP_OPENL + GAP_EMISSIONL, f2);
                    f2 = Maths.logAdd(arrf[n3 + n4 + 2] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n3 + n4 + 1] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[2 + n4] = Maths.logAdd(arrf[n3 + n4 + 0] + GAP_OPENL + GAP_EMISSIONL, f2);
                    arrf[3 + n4] = Maths.logAdd(arrf[n + n4 + 0] + JUNK_OPENL + GAP_EMISSIONL, arrf[n + n4 + 3] + JUNK_EXTENDL + GAP_EMISSIONL);
                    arrf[4 + n4] = Maths.logAdd(arrf[n3 + n4 + 0] + JUNK_OPENL + GAP_EMISSIONL, arrf[n3 + n4 + 4] + JUNK_EXTENDL + GAP_EMISSIONL);
                }
            };
        }

    }
    public static final class ThreeCell
    extends BasicCell {
        static final int MATCH_STATE = 0;
        static final int LEFT_GAP_STATE = 1;
        static final int RIGHT_GAP_STATE = 2;
        static final int INVERSE_MATCH_STATE = 2;
        static final int INVERSE_LEFT_GAP_STATE = 1;
        static final int INVERSE_RIGHT_GAP_STATE = 0;
        static final int N_EMISSION = 4;
        static final float MATCH = 0.9727754f;
        static final float GAP_EXTEND = 0.9744453f;
        static final float GAP_SWITCH = 7.3151797E-4f;
        static final float MATCHL = Maths.log(0.9727754f);
        static final float GAP_OPENL = Maths.log(0.0136123f);
        static final float GAP_EXTENDL = Maths.log(0.9744453f);
        static final float GAP_SWITCHL = Maths.log(7.3151797E-4f);
        static final float GAP_CLOSEL = Maths.log(0.024823198f);
        static final float MATCH_EMISSION_N_L = Maths.log(0.04f);
        static final float MATCH_EMISSIONL = Maths.log(0.12064298f);
        static final float TRANSVERSION_EMISSIONL = Maths.log(0.0103672715f);
        static final float TRANSITION_EMISSIONL = Maths.log(0.018622477f);
        static final float GAP_EMISSIONL = Maths.log(0.2f);
        static final float[] emit = new float[]{MATCH_EMISSIONL, TRANSVERSION_EMISSIONL, TRANSITION_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSION_N_L, TRANSVERSION_EMISSIONL, MATCH_EMISSIONL, TRANSVERSION_EMISSIONL, TRANSITION_EMISSIONL, MATCH_EMISSION_N_L, TRANSITION_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSION_N_L, TRANSVERSION_EMISSIONL, TRANSITION_EMISSIONL, TRANSVERSION_EMISSIONL, MATCH_EMISSIONL, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L, MATCH_EMISSION_N_L};

        public ThreeCell() {
            this.translateChars = Array.matrix1DLookUp(Cell.translateAlphabetChar(new String[]{"aA", "cC", "gG", "tT", "nNxX"}, 4));
            this.startStates = new float[]{Maths.log(0.33333334f), Maths.log(0.33333334f), Maths.log(0.33333334f)};
            this.endStates = new float[]{Maths.log(0.33333334f), Maths.log(0.33333334f), Maths.log(0.33333334f)};
            this.stateNumber = 3;
            this.alphabetSize = 5;
        }

        public CellCalculator backwards(final int n, final int n2, final int n3, final Function_Int function_Int, final Function_Int function_Int2) {
            return new CellCalculator(){

                public final void calc(float[] arrf, int n4, int n22, int n32) {
                    n22 = function_Int.fn(n22 + 1);
                    n32 = function_Int2.fn(n32 + 1);
                    float f = emit[n22 * 5 + n32];
                    float f2 = Maths.logAdd(arrf[n + n4 + 1], arrf[n3 + n4 + 0]);
                    arrf[2 + n4] = Maths.logAdd(arrf[n2 + n4 + 2] + MATCHL + f, f2 + GAP_OPENL + GAP_EMISSIONL);
                    float f3 = arrf[n2 + n4 + 2] + GAP_CLOSEL + f;
                    f2 = Maths.logAdd(arrf[n + n4 + 1] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n3 + n4 + 0] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[1 + n4] = Maths.logAdd(f3, f2);
                    f2 = Maths.logAdd(arrf[n3 + n4 + 0] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n + n4 + 1] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[0 + n4] = Maths.logAdd(f3, f2);
                }
            };
        }

        public CellCalculator forwards(final int n, final int n2, final int n3, final Function_Int function_Int, final Function_Int function_Int2) {
            return new CellCalculator(){

                public final void calc(float[] arrf, int n4, int n22, int n32) {
                    n22 = function_Int.fn(n22);
                    n32 = function_Int2.fn(n32);
                    float f = emit[n22 * 5 + n32];
                    float f2 = Maths.logAdd(arrf[n2 + n4 + 1], arrf[n2 + n4 + 2]) + GAP_CLOSEL;
                    arrf[0 + n4] = Maths.logAdd(arrf[n2 + n4 + 0] + MATCHL, f2) + f;
                    f2 = Maths.logAdd(arrf[n + n4 + 1] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n + n4 + 2] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[1 + n4] = Maths.logAdd(arrf[n + n4 + 0] + GAP_OPENL + GAP_EMISSIONL, f2);
                    f2 = Maths.logAdd(arrf[n3 + n4 + 2] + GAP_EXTENDL + GAP_EMISSIONL, arrf[n3 + n4 + 1] + GAP_SWITCHL + GAP_EMISSIONL);
                    arrf[2 + n4] = Maths.logAdd(arrf[n3 + n4 + 0] + GAP_OPENL + GAP_EMISSIONL, f2);
                }
            };
        }

    }
    public static abstract class BasicCell {
        Function_Int translateChars;
        public float[] startStates;
        public float[] endStates;
        public int stateNumber;
        public int alphabetSize;

        public GetCellCalculator getBackwardCellCalculator(final int n, final int n2, final int n3) {
            return new GetCellCalculator(){

                public CellCalculator getCellCalculator(Function_Int function_Int, Function_Int function_Int2) {
                    return BasicCell.this.backwards(n, n2, n3, function_Int, function_Int2);
                }
            };
        }

        public GetCellCalculator getForwardCellCalculator(final int n, final int n2, final int n3) {
            return new GetCellCalculator(){

                public CellCalculator getCellCalculator(Function_Int function_Int, Function_Int function_Int2) {
                    return BasicCell.this.forwards(n, n2, n3, function_Int, function_Int2);
                }
            };
        }

        public abstract CellCalculator backwards(int var1, int var2, int var3, Function_Int var4, Function_Int var5);

        public abstract CellCalculator forwards(int var1, int var2, int var3, Function_Int var4, Function_Int var5);

    }
    public static interface CellCalculator {
        public void calc(float[] var1, int var2, int var3, int var4);
    }
    public static interface GetCellCalculator {
        public CellCalculator getCellCalculator(Function_Int var1, Function_Int var2);
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.Array;
import bp.common.fp.Function_2Args;
import bp.common.fp.Function_Int_2Args;
import bp.common.fp.Functions_Int_2Args;
import bp.common.fp.Generator;
import bp.common.fp.GeneratorIterator;
import bp.common.fp.GeneratorTools;
import bp.common.fp.Generators;
import bp.common.fp.IterationTools;
import bp.common.fp.Iterators;
import bp.common.fp.Predicate;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_Int;
import bp.common.io.CigarParser_Generator;
import bp.common.io.ExternalExecution;
import bp.common.io.FastaOutput_Procedure_Int;
import bp.common.maths.Maths;
import bp.pecan.PolygonFiller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Chains {
    static final Logger logger = Logger.getLogger(Chains.class.getName());
    static int noOfLongGapOptimisations = 0;

    public static Iterator filterCutPoints(List list, List list2, int n) {
        int n2;
        int[] arrn = new int[list.size()];
        int n3 = 0;
        int n4 = Integer.MIN_VALUE;
        for (CutPoint cutPoint : list) {
            n2 = cutPoint.x + cutPoint.y;
            arrn[n3++] = n2;
            if (n2 <= n4) {
                throw new IllegalStateException(n4 + " " + n2);
            }
            n4 = n2;
        }
        final HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int[] arrn2 : list2) {
            int n5;
            n2 = arrn2[2] + arrn2[3];
            if (n2 < (n5 = arrn2[0] + arrn2[1])) {
                throw new IllegalStateException(n2 + " " + n5);
            }
            int n6 = Array.binarySearch(n5, arrn);
            while (n6 < arrn.length && arrn[n6] - n <= n2) {
                hashSet.add(new Integer(arrn[n6++]));
            }
        }
        return Iterators.filter(list.iterator(), new Predicate(){

            public boolean test(Object object) {
                CutPoint cutPoint = (CutPoint)object;
                return !hashSet.contains(new Integer(cutPoint.x + cutPoint.y));
            }
        });
    }

    public static final List breaks(PrimeConstraints primeConstraints, int n, int n2) {
        Object object2;
        List list = Chains.breaksP(primeConstraints, n, n2);
        List list2 = Chains.breaksP(primeConstraints, n2, n);
        for (Object object2 : list2) {
            Array.mingle(object2, ((int[])object2).length);
        }
        list.addAll(list2);
        Object object3 = new Comparator(){

            public int compare(Object object, Object object2) {
                int[] arrn = (int[])object;
                int n = arrn[0] + arrn[1];
                int[] arrn2 = (int[])object2;
                int n2 = arrn2[0] + arrn2[1];
                if (n < n2) {
                    return -1;
                }
                if (n > n2) {
                    return 1;
                }
                return 0;
            }
        };
        Collections.sort(list, object3);
        object3 = new Comparator(){

            public int compare(Object object, Object object2) {
                int[] arrn = (int[])object;
                int n = arrn[0] + arrn[1];
                int[] arrn2 = (int[])object2;
                int n2 = arrn2[0] + arrn2[1];
                if (n == n2) {
                    return 0;
                }
                return -1;
            }
        };
        object2 = new Comparator(){

            public int compare(Object object, Object object2) {
                int[] arrn = (int[])object;
                int n = arrn[2] + arrn[3];
                int[] arrn2 = (int[])object2;
                int n2 = arrn2[2] + arrn2[3];
                if (n == n2) {
                    return 0;
                }
                return -1;
            }
        };
        Function_2Args function_2Args = new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return object2;
            }
        };
        Function_2Args function_2Args2 = new Function_2Args(){

            public Object fn(Object object, Object object2) {
                return object;
            }
        };
        return (List)IterationTools.append(Iterators.uniq(Iterators.uniq(list.iterator(), (Comparator)object3, function_2Args), (Comparator)object2, function_2Args2), new LinkedList());
    }

    static final List breaksP(PrimeConstraints primeConstraints, int n, int n2) {
        LinkedList<int[]> linkedList = new LinkedList<int[]>();
        SortedSet sortedSet = primeConstraints.primeConstraints[n2][n];
        SortedSet sortedSet2 = primeConstraints.primeConstraints[n][n2];
        sortedSet2 = sortedSet2.headSet(sortedSet2.last());
        for (PrimeConstraints.ConstraintNode constraintNode : sortedSet2) {
            int n3;
            int n4;
            if (constraintNode.maxOffset == Integer.MIN_VALUE) {
                n4 = constraintNode.x;
                n3 = constraintNode.y - 1;
            } else {
                n4 = constraintNode.x + constraintNode.maxOffset;
                n3 = constraintNode.y + constraintNode.maxOffset;
            }
            PrimeConstraints.ConstraintNode constraintNode2 = PrimeConstraints.getRightMostPointAffectedByConstraint(sortedSet, n4 + 1);
            if (constraintNode2.maxOffset == Integer.MIN_VALUE) {
                if (constraintNode2.y != n4 + 1 || constraintNode2.x < n3) continue;
                linkedList.add(new int[]{n4, n3, n4, constraintNode2.x});
                continue;
            }
            if (constraintNode.maxOffset != Integer.MIN_VALUE) continue;
            if (constraintNode2.y == n4 + 1) {
                if (constraintNode2.x <= n3) continue;
                linkedList.add(new int[]{n4, n3, n4, constraintNode2.x - 1});
                continue;
            }
            if (constraintNode2.y + constraintNode2.maxOffset < n4 || constraintNode2.y - constraintNode2.x != n4 - n3) continue;
            linkedList.add(new int[]{n4, n3, n4, n3});
        }
        return linkedList;
    }

    public static void transformConstraints(int n, PrimeConstraints primeConstraints) {
        for (int i = 0; i < primeConstraints.sequenceNumber; ++i) {
            block1: for (int j = 0; j < primeConstraints.sequenceNumber; ++j) {
                if (i == j) continue;
                SortedSet sortedSet = primeConstraints.primeConstraints[i][j];
                PrimeConstraints.ConstraintNode.compareX = true;
                Iterator iterator = sortedSet.iterator();
                iterator.next();
                do {
                    PrimeConstraints.ConstraintNode constraintNode = (PrimeConstraints.ConstraintNode)iterator.next();
                    if (constraintNode.x == Integer.MAX_VALUE) continue block1;
                    constraintNode.x -= n;
                    constraintNode.y += n;
                } while (true);
            }
        }
    }

    public static void makeMultipleAlignment(Procedure_Int[] arrprocedure_Int, Generator[][] arrgenerator, byte[][] arrby, int[] arrn, int n, int[] arrn2, byte by) {
        int n2;
        int n3;
        int[] arrn3 = new int[n];
        Generator[][] arrgenerator2 = new Generator[n][n];
        int[][] arrn4 = new int[n][n];
        for (n3 = 0; n3 < n; ++n3) {
            for (n2 = n3 + 1; n2 < n; ++n2) {
                Generator generator;
                arrgenerator2[n3][n2] = generator = Chains.nextPairs(arrgenerator[n3][n2], arrn[n3], arrn[n2]);
                int[] arrn5 = (int[])generator.gen();
                arrn4[n3][n2] = arrn5[0];
                arrn4[n2][n3] = arrn5[1];
            }
        }
        for (n3 = 0; n3 < n; ++n3) {
            n2 = arrn[n3];
            while (arrn3[n3] < n2) {
                Chains.makeMultipleAlignment(arrprocedure_Int, arrgenerator2, arrn4, arrby, arrn3, n3, arrn2, 0, by);
            }
        }
    }

    public static Generator nextPairs(final Generator generator, final int n, final int n2) {
        return new Generator(){
            List<int[]> l = new LinkedList<int[]>();

            public Object gen() {
                if (this.l.size() != 0) {
                    return this.l.remove(0);
                }
                PolygonFiller.Node node = (PolygonFiller.Node)generator.gen();
                if (node == null) {
                    return new int[]{n, n2};
                }
                int n3 = node.y;
                int n22 = node.x;
                while (n3 <= node.yMax) {
                    this.l.add(new int[]{n22++, n3++});
                }
                return this.l.remove(0);
            }
        };
    }

    public static void makeMultipleAlignment(Procedure_Int[] arrprocedure_Int, Generator[][] arrgenerator, int[][] arrn, byte[][] arrby, int[] arrn2, int n, int[] arrn3, int n2, byte by) {
        int n3 = Chains.getMins(arrn[n], arrn3, n2, n);
        int n4 = arrn[n][arrn3[n2]];
        if (arrn2[n] < n4) {
            do {
                for (int i = 0; i < arrprocedure_Int.length; ++i) {
                    if (i == n) continue;
                    arrprocedure_Int[i].pro(by);
                }
                int n5 = n;
                int n6 = arrn2[n5];
                arrn2[n5] = n6 + 1;
                arrprocedure_Int[n].pro(arrby[n][n6]);
            } while (arrn2[n] < n4);
            return;
        }
        n4 = n2 + arrprocedure_Int.length;
        Arrays.fill(arrn3, n4, n4 + arrprocedure_Int.length, 0);
        do {
            int n7;
            block13: {
                int n8;
                for (n7 = n2; n7 < n3; ++n7) {
                    n8 = arrn3[n7];
                    while (arrn2[n8] < arrn[n8][n]) {
                        Chains.makeMultipleAlignment(arrprocedure_Int, arrgenerator, arrn, arrby, arrn2, n8, arrn3, n4 + arrprocedure_Int.length, by);
                    }
                }
                int n9 = n4 + n;
                arrn3[n9] = arrn3[n9] | 2;
                for (n7 = n2; n7 < n3; ++n7) {
                    int n10 = n4 + arrn3[n7];
                    arrn3[n10] = arrn3[n10] | 1;
                }
                for (n7 = 0; n7 < arrprocedure_Int.length; ++n7) {
                    if (arrn3[n4 + n7] != 1) {
                        continue;
                    }
                    break block13;
                }
                n3 = n2;
                for (n7 = 0; n7 < arrprocedure_Int.length; ++n7) {
                    if (arrn3[n4 + n7] == 3) {
                        int n11 = n7;
                        int n12 = arrn2[n11];
                        arrn2[n11] = n12 + 1;
                        arrprocedure_Int[n7].pro(arrby[n7][n12]);
                        arrn3[n3++] = n7;
                        continue;
                    }
                    arrprocedure_Int[n7].pro(by);
                }
                for (n7 = n2; n7 < n3; ++n7) {
                    n8 = arrn3[n7];
                    for (int i = n7 + 1; i < n3; ++i) {
                        int n13 = arrn3[i];
                        if (arrn[n8][n13] != arrn2[n8] - 1) continue;
                        int[] arrn4 = (int[])arrgenerator[n8][n13].gen();
                        arrn[n8][n13] = arrn4[0];
                        arrn[n13][n8] = arrn4[1];
                    }
                }
                return;
            }
            n = n7;
            n3 = Chains.getMins(arrn[n], arrn3, n2, n);
        } while (true);
    }

    public static PrimeConstraints loadConstraintsFromAlignment(String string, int n) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(bufferedInputStream));
        PrimeConstraints primeConstraints = new PrimeConstraints(n);
        String string2 = lineNumberReader.readLine();
        while (string2 != null) {
            String[] arrstring = string2.split("\\s+");
            assert (arrstring.length % 2 == 1);
            assert (arrstring.length >= 3);
            int n2 = Integer.parseInt(arrstring[0]);
            int n3 = Integer.parseInt(arrstring[1]);
            int n4 = Integer.parseInt(arrstring[2]);
            for (int i = 3; i < arrstring.length; i += 2) {
                int n5 = Integer.parseInt(arrstring[i + 0]);
                int n6 = Integer.parseInt(arrstring[i + 1]);
                logger.info(n2 + " " + n3 + " " + n4 + " " + n5 + " " + n6 + "\n");
                primeConstraints.updatePrimeConstraints(n3, n5, n4, n6, n2);
                primeConstraints.updatePrimeConstraints(n5, n3, n6, n4, n2);
            }
            string2 = lineNumberReader.readLine();
        }
        ((InputStream)bufferedInputStream).close();
        return primeConstraints;
    }

    static int getMins(int[] arrn, int[] arrn2, int n, int n2) {
        int n3 = Integer.MAX_VALUE;
        int n4 = n;
        for (int i = 0; i < arrn.length; ++i) {
            if (i == n2) continue;
            int n5 = arrn[i];
            if (n5 < n3) {
                n3 = n5;
                n4 = n + 1;
                arrn2[n] = i;
                continue;
            }
            if (n5 != n3) continue;
            arrn2[n4++] = i;
        }
        return n4;
    }

    static final List overlap(List list, Function_Int_2Args function_Int_2Args) {
        LinkedList<Node> linkedList = new LinkedList<Node>();
        if (list.size() == 0) {
            return linkedList;
        }
        Comparator comparator = new Comparator(){

            public int compare(Object object, Object object2) {
                Node node = (Node)object;
                Node node2 = (Node)object2;
                int n = node.xS - node.n.xS;
                int n2 = node2.xS - node2.n.xS;
                return n < n2 ? -1 : (n > n2 ? 1 : (node.xS < node2.xS ? -1 : (node.xS > node2.xS ? 1 : 0)));
            }
        };
        Collections.sort(list, comparator);
        Node node = (Node)list.remove(0);
        int n = node.xS - node.n.xS;
        while (list.size() != 0) {
            Node node2 = (Node)list.remove(0);
            int n2 = node2.xS - node2.n.xS;
            if (n == n2 && node.xE >= node2.xS) {
                if (node.xS < node2.xS) {
                    Node node3;
                    Node node4 = new Node(node.xS, node2.xS - 1, node.score, null, null);
                    node4.n = node3 = new Node(node.n.xS, node2.n.xS - 1, node.score, node4, null);
                    linkedList.add(node4);
                    node.xS = node2.xS;
                    node.n.xS = node2.n.xS;
                }
                if (node.xE > node2.xE) {
                    node2.n.score = node2.score = function_Int_2Args.fn(node.score, node2.score);
                    node.xS = node2.xE + 1;
                    node.n.xS = node2.n.xE + 1;
                    Chains.insert(list, node, comparator);
                    node = node2;
                    continue;
                }
                if (node2.xE > node.xE) {
                    node.n.score = node.score = function_Int_2Args.fn(node.score, node2.score);
                    node2.xS = node.xE + 1;
                    node2.n.xS = node.n.xE + 1;
                    Chains.insert(list, node2, comparator);
                    continue;
                }
                node.n.score = node.score = function_Int_2Args.fn(node.score, node2.score);
                continue;
            }
            linkedList.add(node);
            n = n2;
            node = node2;
        }
        linkedList.add(node);
        Collections.sort(linkedList, Chains.nodeComparator());
        return linkedList;
    }

    static void insert(List list, Node node, Comparator comparator) {
        ListIterator<Node> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Node node2 = (Node)listIterator.next();
            if (comparator.compare(node, node2) > 0) continue;
            listIterator.previous();
            listIterator.add(node);
            return;
        }
        list.add(node);
    }

    static Node getNext(List list, List list2, Comparator<Object> comparator) {
        if (list.size() != 0) {
            Object e = list.get(0);
            if (list2.size() != 0) {
                Object e2 = list2.get(0);
                return (Node)(comparator.compare(e, e2) == 1 ? list2.remove(0) : list.remove(0));
            }
            return (Node)list.remove(0);
        }
        if (list2.size() != 0) {
            return (Node)list2.remove(0);
        }
        return null;
    }

    static final Node overlapAndMerge(Node node, Node node2, Function_Int_2Args function_Int_2Args) {
        LinkedList<Node> linkedList = new LinkedList<Node>();
        while (node != null) {
            linkedList.add(node);
            node = node.p;
        }
        while (node2 != null) {
            linkedList.add(node2);
            node2 = node2.p;
        }
        return Chains.joinUpNodes(Chains.overlap(linkedList, function_Int_2Args));
    }

    static final Node merge(Node node, Node node2) {
        Node node3;
        if (node == null) {
            return node2;
        }
        if (node2 == null) {
            return node;
        }
        if (node.xS < node2.xS || node.xS == node2.xS && node.n.xS < node2.n.xS) {
            node3 = node;
            node = node2;
            node2 = node3;
        }
        node3 = node2;
        while (node != null) {
            Node node4 = node.p;
            Chains.insert(node, node2);
            node2 = node;
            node = node4;
        }
        return node3;
    }

    static final void insert(Node node, Node node2) {
        while (node2.p != null && (node2.p.xS < node.xS || node2.p.xS == node.xS && node2.p.n.xS < node.n.xS)) {
            node2 = node2.p;
        }
        node.p = node2.p;
        node2.p = node;
    }

    static void checkOrderingOfNet(Node node, boolean bl) {
        int n = Integer.MIN_VALUE;
        int n2 = Integer.MIN_VALUE;
        while (node != null) {
            if (node.xE - node.xS != node.n.xE - node.n.xS) {
                throw new IllegalStateException();
            }
            if (node.score != node.n.score) {
                throw new IllegalStateException();
            }
            if (node != node.n.n) {
                throw new IllegalStateException();
            }
            if (n > node.xS) {
                throw new IllegalStateException();
            }
            if (bl) {
                if (n2 > node.n.xS) {
                    throw new IllegalStateException();
                }
                if (n2 >= node.n.xS) {
                    throw new IllegalStateException();
                }
            }
            n = node.xS;
            n2 = node.p == null || node.p.xS > node.xS ? Integer.MIN_VALUE : node.n.xS;
            node = node.p;
        }
    }

    static final Node consistencyChains(Node node, Node node2, Function_Int_2Args function_Int_2Args, Function_Int_2Args function_Int_2Args2) {
        final LinkedList linkedList = new LinkedList();
        Chains.consistencyChains(node, node2, new Procedure(){

            public void pro(Object object) {
                linkedList.add(object);
            }
        }, function_Int_2Args2);
        List list = Chains.overlap(linkedList, function_Int_2Args);
        return Chains.joinUpNodes(list);
    }

    static final Node joinUpNodes(List list) {
        Iterator iterator = list.iterator();
        if (iterator.hasNext()) {
            Node node;
            Node node2 = node = (Node)iterator.next();
            while (iterator.hasNext()) {
                Node node3;
                node.p = node3 = (Node)iterator.next();
                node = node3;
            }
            node.p = null;
            return node2;
        }
        return null;
    }

    static final Comparator nodeComparator() {
        return new Comparator(){

            public int compare(Object object, Object object2) {
                Node node = (Node)object;
                Node node2 = (Node)object2;
                return node.xS < node2.xS ? -1 : (node.xS > node2.xS ? 1 : (node.n.xS < node2.n.xS ? -1 : (node.n.xS > node2.n.xS ? 1 : 0)));
            }
        };
    }

    public static final Node sortOppositeChain(Node node) {
        Node node2;
        LinkedList<Node> linkedList = new LinkedList<Node>();
        while (node != null) {
            linkedList.add(node.n);
            node = node.p;
        }
        Collections.sort(linkedList, Chains.nodeComparator());
        Node node3 = node2 = new Node();
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            node2 = node2.p = (Node)iterator.next();
        }
        node2.p = null;
        return node3.p;
    }

    static final void consistencyChains(Node node, Node node2, Procedure procedure, Function_Int_2Args function_Int_2Args) {
        if (node2 == null) {
            return;
        }
        while (node != null && node2 != null) {
            while (node.xS > node2.xE) {
                node2 = node2.p;
                if (node2 != null) continue;
                return;
            }
            Node node3 = node2;
            block2 : while (node.xE >= node3.xS) {
                Node node4;
                int n;
                int n2;
                int n3;
                int n4;
                int n5;
                if (node.xS < node3.xS) {
                    n = node3.xS - node.xS;
                    n3 = node.n.xS + n;
                    n2 = node3.n.xS;
                } else {
                    n = node.xS - node3.xS;
                    n3 = node.n.xS;
                    n2 = node3.n.xS + n;
                }
                if (node.xE > node3.xE) {
                    n = node.xE - node3.xE;
                    n5 = node.n.xE - n;
                    n4 = node3.n.xE;
                } else {
                    n = node3.xE - node.xE;
                    n5 = node.n.xE;
                    n4 = node3.n.xE - n;
                }
                n = (node.score + node3.score) / 2;
                Node node5 = new Node(n3, n5, n, null, null);
                node5.n = node4 = new Node(n2, n4, n, node5, null);
                procedure.pro(node5);
                node3 = node3.p;
                if (node3 == null) break;
                while (node.xS > node3.xE) {
                    node3 = node3.p;
                    if (node3 != null) continue;
                    break block2;
                }
            }
            node = node.p;
        }
    }

    public static final Node convertEdgeListToNodeChain(Generator generator) {
        PolygonFiller.Node node;
        Node node2;
        Node node3 = node2 = new Node();
        while ((node = (PolygonFiller.Node)generator.gen()) != null) {
            Node node4;
            Node node5 = new Node(node.x, node.x + node.yMax - node.y, node.z, null, null);
            node5.n = node4 = new Node(node.y, node.yMax, node.z, node5, null);
            node2.p = node5;
            node2 = node5;
        }
        return node3.p;
    }

    static final List localAlignmentsInGaps(Generator generator, Aligner aligner, int n, int n2, int n3, int n4, int n5, int n6, byte[] arrby, byte[] arrby2) {
        int[] arrn;
        LinkedList linkedList = new LinkedList();
        Function_Int_2Args function_Int_2Args = new Function_Int_2Args(){

            public int fn(int n, int n2) {
                return (n + n2) / 2;
            }
        };
        Generator generator2 = PolygonFiller.nERPoints(generator, n * 2, n3 - 1, n4 - 1, n3 + n5, n4 + n6);
        while ((arrn = (int[])generator2.gen()) != null) {
            n3 = arrn[0] + 1;
            n4 = arrn[1] + 1;
            n5 = arrn[2] - arrn[0] - 1;
            n6 = arrn[3] - arrn[1] - 1;
            if (n5 < n || n6 < n) continue;
            if (n2 > n5 + n6) {
                IterationTools.append(aligner.align(arrby, arrby2, n3, n5, n4, n6), linkedList);
                continue;
            }
            int n7 = n5 > n2 / 2 ? n2 / 2 : n5;
            int n8 = n6 > n2 / 2 ? n2 / 2 : n6;
            Node node = Chains.convertEdgeListToNodeChain(Generators.iteratorGenerator(aligner.align(arrby, arrby2, n3, n7, n4, n8)));
            n3 = n3 + n5 - n7;
            n4 = n4 + n6 - n8;
            Node node2 = Chains.convertEdgeListToNodeChain(Generators.iteratorGenerator(aligner.align(arrby, arrby2, n3, n7, n4, n8)));
            node = Chains.overlapAndMerge(node, node2, function_Int_2Args);
            GeneratorTools.append(Chains.convertNodeChainToEdgeList(node), linkedList);
        }
        return linkedList;
    }

    static final Node copyOfChain(Node node) {
        if (node != null) {
            Node node2;
            Node node3 = new Node(node.xS, node.xE, node.score, null, null);
            node3.n = node2 = new Node(node.n.xS, node.n.xE, node.n.score, node3, null);
            Node node4 = node3;
            node = node.p;
            while (node != null) {
                Node node5;
                Node node6 = new Node(node.xS, node.xE, node.score, null, null);
                node6.n = node5 = new Node(node.n.xS, node.n.xE, node.n.score, node6, null);
                node3.p = node6;
                node3 = node6;
                node = node.p;
            }
            return node4;
        }
        return null;
    }

    static final Node[][] makeConsistentChains(Node[][] arrnode, int[][] arrn, PrimeConstraints primeConstraints, int n, boolean bl) {
        Node[][] arrnode2 = new Node[n][n];
        Function_Int_2Args function_Int_2Args = Functions_Int_2Args.sum();
        Function_Int_2Args function_Int_2Args2 = Functions_Int_2Args.sum();
        Function_Int_2Args function_Int_2Args3 = Functions_Int_2Args.min();
        for (int i = 0; i < arrn.length; ++i) {
            int[] arrn2 = arrn[i];
            int n2 = arrn2[0];
            int n3 = arrn2[1];
            logger.info(" pair " + i + " " + n2 + " " + n3);
            Node node = Chains.copyOfChain(arrnode[n2][n3]);
            if (bl) {
                for (int j = 0; j < n; ++j) {
                    if (j == n2 || j == n3) continue;
                    Node node2 = Chains.consistencyChains(arrnode[j][n2], arrnode[j][n3], function_Int_2Args2, function_Int_2Args3);
                    node = Chains.overlapAndMerge(node2, node, function_Int_2Args);
                }
            }
            node = primeConstraints.filterByConstraints(n2, n3, node);
            node = Chains.sortOppositeChain(node);
            node = primeConstraints.filterByConstraints(n3, n2, node);
            Chains.multiplyScoresByLengths(node);
            node = Chains.getHighestChain(node);
            Chains.divideScoresByLengths(node);
            arrnode2[n3][n2] = node;
            primeConstraints.updatePrimeConstraints(n3, n2, node);
            arrnode2[n2][n3] = node = Chains.sortOppositeChain(node);
            primeConstraints.updatePrimeConstraints(n2, n3, node);
        }
        return arrnode2;
    }

    public static Node trim(Node node, int n) {
        int n2 = n * 2;
        do {
            if (node == null) {
                return null;
            }
            if (node.xE - node.xS >= n2) {
                node.xS += n;
                node.xE -= n;
                node.n.xS += n;
                node.n.xE -= n;
                break;
            }
            node = node.p;
        } while (true);
        Node node2 = node;
        Node node3 = node;
        node = node.p;
        while (node != null) {
            if (node.xE - node.xS >= n2) {
                node.xS += n;
                node.xE -= n;
                node.n.xS += n;
                node.n.xE -= n;
                node3 = node;
                node = node.p;
                continue;
            }
            node3.p = node.p;
            node = node.p;
        }
        return node2;
    }

    public static final void multiplyScoresByLengths(Node node) {
        while (node != null) {
            node.score *= node.xE - node.xS + 1;
            node.n.score = node.score;
            node = node.p;
        }
    }

    public static final void transformChains(Node node, int n, int n2) {
        while (node != null) {
            node.xE += n;
            node.xS += n;
            node.n.xE += n2;
            node.n.xS += n2;
            node = node.p;
        }
    }

    public static final void makeConsistentChains(PrimeConstraints primeConstraints, int[][] arrn, int n, Aligner[] arraligner, int n2, int n3, int[] arrn2, byte[][] arrby, int[] arrn3, boolean[] arrbl, int n4) {
        for (int i = n2; i >= 0; --i) {
            Chains.makeConsistentChains(primeConstraints, arrn, n, arraligner[i], n3, arrn2[i], arrby, arrn3, arrbl[i], n4);
        }
    }

    public static final void makeConsistentChains(PrimeConstraints primeConstraints, int[][] arrn, int n, Aligner aligner, int n2, int n3, byte[][] arrby, int[] arrn2, boolean bl, int n4) {
        Node[][] arrnode = new Node[n][n];
        for (int i = 0; i < n; ++i) {
            int n5 = arrn2[i];
            byte[] arrby2 = arrby[i];
            for (int j = i + 1; j < n; ++j) {
                int n6 = arrn2[j];
                List list = Chains.localAlignmentsInGaps(primeConstraints.convertPrimeConstraintsToEdgeList(i, j, true), aligner, n2, n3, 0, 0, n5, n6, arrby2, arrby[j]);
                Node node = Chains.convertEdgeListToNodeChain(PolygonFiller.flipEdgeXYDiagonalsCoordinates(Generators.iteratorGenerator(list.iterator())));
                arrnode[j][i] = node = Chains.trim(node, n4);
                arrnode[i][j] = Chains.sortOppositeChain(node);
            }
        }
        Chains.makeConsistentChains(arrnode, arrn, primeConstraints, n, bl);
    }

    static final Node getHighestChain(Node node) {
        return Chains.convertBackwardsCumulativeEdgeNodeToNode(Chains.getHighestChainP(node));
    }

    static final Node convertBackwardsCumulativeEdgeNodeToNode(EdgeNode edgeNode) {
        if (edgeNode != null) {
            Node node;
            Node node2 = null;
            EdgeNode edgeNode2 = edgeNode.eN;
            while (edgeNode2 != null) {
                node2 = new Node(edgeNode.yMin, edgeNode.yMax, (int)(edgeNode.score - edgeNode2.score), null, node2);
                node2.n = node = new Node(edgeNode.xMin, edgeNode.xMin - edgeNode.yMin + edgeNode.yMax, node2.score, node2, null);
                edgeNode = edgeNode2;
                edgeNode2 = edgeNode2.eN;
            }
            node2 = new Node(edgeNode.yMin, edgeNode.yMax, (int)edgeNode.score, null, node2);
            node2.n = node = new Node(edgeNode.xMin, edgeNode.xMin - edgeNode.yMin + edgeNode.yMax, node2.score, node2, null);
            return node2;
        }
        return null;
    }

    static final EdgeNode getHighestChainP(Node node) {
        TreeMap<Integer, Object> treeMap = new TreeMap<Integer, Object>();
        TreeMap<Integer, Object> treeMap2 = new TreeMap<Integer, Object>();
        if (node != null) {
            block0: do {
                int n = node.xS;
                Chains.addInChains(treeMap, treeMap2, n);
                do {
                    EdgeNode edgeNode;
                    SortedMap sortedMap;
                    Object object;
                    if (!(sortedMap = treeMap.headMap(new Integer(node.n.xS))).isEmpty()) {
                        object = (EdgeNode)sortedMap.get(sortedMap.lastKey());
                        edgeNode = new EdgeNode(node.n.xS, n, node.xE, ((EdgeNode)object).score + (long)node.score, (EdgeNode)object);
                    } else {
                        edgeNode = new EdgeNode(node.n.xS, n, node.xE, node.score, null);
                    }
                    if (treeMap2.containsKey(new Integer(edgeNode.yMax))) {
                        object = (List)treeMap2.get(new Integer(edgeNode.yMax));
                        object.add(edgeNode);
                    } else {
                        object = new LinkedList();
                        object.add(edgeNode);
                        treeMap2.put(new Integer(edgeNode.yMax), object);
                    }
                    node = node.p;
                    if (node == null) break block0;
                } while (node.xS == n);
            } while (true);
        }
        Chains.addInChains(treeMap, treeMap2, Integer.MAX_VALUE);
        return !treeMap.isEmpty() ? (EdgeNode)treeMap.get(treeMap.lastKey()) : null;
    }

    static final void addInChains(SortedMap<Integer, Object> sortedMap, SortedMap<Integer, Object> sortedMap2, int n) {
        while (!sortedMap2.isEmpty() && sortedMap2.firstKey() < n) {
            for (EdgeNode edgeNode : (List)sortedMap2.remove(sortedMap2.firstKey())) {
                Integer n2 = new Integer(edgeNode.xMin + edgeNode.yMax - edgeNode.yMin + 1);
                SortedMap<Integer, Object> sortedMap3 = sortedMap.headMap(n2);
                if (!sortedMap3.isEmpty() ? ((EdgeNode)sortedMap3.get((Object)sortedMap3.lastKey())).score >= edgeNode.score : edgeNode.score <= 0L) continue;
                sortedMap3 = sortedMap.tailMap(n2);
                while (!sortedMap3.isEmpty()) {
                    EdgeNode edgeNode2 = (EdgeNode)sortedMap3.get(sortedMap3.firstKey());
                    if (edgeNode2.score > edgeNode.score) break;
                    sortedMap3.remove(sortedMap3.firstKey());
                }
                sortedMap.put(new Integer(n2 - 1), edgeNode);
            }
        }
    }

    static final String[] turnOffBSDP(String[] arrstring) {
        arrstring = (String[])arrstring.clone();
        int n = Array.indexOf(arrstring, "--gappedextension");
        arrstring[n + 1] = "false";
        return arrstring;
    }

    static final boolean isGappedExtensionOn(String[] arrstring) {
        int n = Array.indexOf(arrstring, "--gappedextension");
        return n != Integer.MAX_VALUE && arrstring[n + 1].equals("true");
    }

    static final Generator exonerateCigarGenerator(String[] arrstring) {
        LinkedList<CigarParser_Generator.Cigar> linkedList = new LinkedList<CigarParser_Generator.Cigar>();
        try {
            String[] arrstring2;
            CigarParser_Generator.Cigar cigar;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (ExternalExecution.runSaveOutput(arrstring, byteArrayOutputStream, null) != 0) {
                if (!Chains.isGappedExtensionOn(arrstring)) {
                    throw new IllegalStateException();
                }
                logger.info("Exonerate failed, gapped extension is on, trying BSDP instead");
                byteArrayOutputStream.close();
                byteArrayOutputStream = new ByteArrayOutputStream();
                arrstring2 = Chains.turnOffBSDP(arrstring);
                if (ExternalExecution.runSaveOutput(arrstring2, byteArrayOutputStream, null) != 0) {
                    throw new IllegalStateException();
                }
            }
            byteArrayOutputStream.flush();
            arrstring2 = new LineNumberReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
            while ((cigar = CigarParser_Generator.parseCigar((LineNumberReader)arrstring2)) != null) {
                linkedList.add(cigar);
            }
            arrstring2.close();
            byteArrayOutputStream.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            throw new IllegalArgumentException();
        }
        return Generators.iteratorGenerator(linkedList.iterator());
    }

    static final List<List> exonerateAlignment(String[] arrstring) {
        CigarParser_Generator.Cigar cigar;
        Generator generator = Chains.exonerateCigarGenerator(arrstring);
        LinkedList<List> linkedList = new LinkedList<List>();
        while ((cigar = (CigarParser_Generator.Cigar)generator.gen()) != null) {
            if (cigar.queryStrand != 0 || cigar.targetStrand != 0) continue;
            List list = (List)IterationTools.append(new GeneratorIterator(CigarParser_Generator.convertToEdgeList(cigar)), new LinkedList());
            int n = 0;
            for (PolygonFiller.Node node : list) {
                n += node.yMax - node.y + 1;
            }
            for (PolygonFiller.Node node : list) {
                node.z = 100 * cigar.score / n;
                if (node.z > 0) continue;
                throw new IllegalStateException(cigar.score + " " + n);
            }
            linkedList.add(list);
        }
        return linkedList;
    }

    public static final int[] countAlignments(byte[] arrby, byte[] arrby2, List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (PolygonFiller.Node node : list) {
            int n5 = node.y;
            int n6 = node.yMax;
            int n7 = node.x;
            while (n5 <= n6) {
                if (arrby2[n5] == arrby[n7]) {
                    switch (arrby2[n5]) {
                        case 65: 
                        case 97: {
                            ++n;
                            break;
                        }
                        case 67: 
                        case 99: {
                            ++n2;
                            break;
                        }
                        case 71: 
                        case 103: {
                            ++n3;
                            break;
                        }
                        case 84: 
                        case 116: {
                            ++n4;
                        }
                    }
                }
                ++n5;
                ++n7;
            }
        }
        return new int[]{n, n2, n3, n4};
    }

    public static float relativeEntropy(int n, int n2, int n3, int n4, float[] arrf) {
        int n5 = n + n2 + n3 + n4;
        if (n5 == 0) {
            return 1.0f;
        }
        if (n5 < arrf.length) {
            return -((arrf[n] * (float)n + arrf[n2] * (float)n2 + arrf[n3] * (float)n3 + arrf[n4] * (float)n4) / (float)n5 - arrf[n5]) / arrf[4];
        }
        return -((Chains.getTimesLogExcludingZero(n) + Chains.getTimesLogExcludingZero(n2) + Chains.getTimesLogExcludingZero(n3) + Chains.getTimesLogExcludingZero(n4)) / (float)n5 - Maths.log(n5)) / arrf[4];
    }

    public static final float getTimesLogExcludingZero(int n) {
        return n == 0 ? 0.0f : Maths.log(n) * (float)n;
    }

    public static final LocalAligner rescoreAlignments(final LocalAligner localAligner, final float f) {
        return new LocalAligner(){
            float[] logs = new float[300];
            {
                for (int i = 1; i < this.logs.length; ++i) {
                    this.logs[i] = Maths.log(i);
                }
            }

            @Override
            public List<List> align(byte[] arrby, byte[] arrby2, int n, int n2, int n3, int n4) {
                List<List> list = localAligner.align(arrby, arrby2, n, n2, n3, n4);
                ListIterator<List> listIterator = list.listIterator();
                while (listIterator.hasNext()) {
                    List list2 = listIterator.next();
                    int[] arrn = Chains.countAlignments(arrby, arrby2, list2);
                    float f2 = Chains.relativeEntropy(arrn[0], arrn[1], arrn[2], arrn[3], this.logs);
                    if (f2 < f) {
                        listIterator.remove();
                        continue;
                    }
                    for (PolygonFiller.Node node : list2) {
                        node.z = (int)((float)node.z * f2);
                    }
                }
                return list;
            }
        };
    }

    public static final Aligner alignerConvertor(final LocalAligner localAligner) {
        return new Aligner(){

            public Iterator align(byte[] arrby, byte[] arrby2, int n, int n2, int n3, int n4) {
                List<List> list = localAligner.align(arrby, arrby2, n, n2, n3, n4);
                LinkedList linkedList = new LinkedList();
                Iterator<List> iterator = list.iterator();
                while (iterator.hasNext()) {
                    linkedList.addAll(iterator.next());
                }
                Collections.sort(linkedList);
                return linkedList.iterator();
            }
        };
    }

    public static final LocalAligner makeExonerateAlignment(final String[] arrstring) {
        return new LocalAligner(){
            String[] commandsPlusFiles;
            File f;
            File f2;
            {
                this.commandsPlusFiles = new String[arrstring.length + 2];
                try {
                    this.f = File.createTempFile("temp", ".fa");
                    this.f2 = File.createTempFile("temp", ".fa");
                    this.f.deleteOnExit();
                    this.f2.deleteOnExit();
                }
                catch (IOException iOException) {
                    throw new IllegalStateException();
                }
                System.arraycopy(arrstring, 0, this.commandsPlusFiles, 0, arrstring.length);
            }

            @Override
            public final List<List> align(byte[] arrby, byte[] arrby2, int n, int n2, int n3, int n4) {
                Chains.fastaSubSeq(arrby, n, n2, "one", this.f);
                Chains.fastaSubSeq(arrby2, n3, n4, "two", this.f2);
                this.commandsPlusFiles[arrstring.length] = this.f.toString();
                this.commandsPlusFiles[arrstring.length + 1] = this.f2.toString();
                List<List> list = Chains.exonerateAlignment(this.commandsPlusFiles);
                Iterator<List> iterator = list.iterator();
                while (iterator.hasNext()) {
                    PolygonFiller.transformEdgeList(iterator.next(), n, n3);
                }
                if (!this.f.delete() || !this.f2.delete()) {
                    throw new IllegalStateException();
                }
                return list;
            }
        };
    }

    public static final void fastaSubSeq(byte[] arrby, int n, int n2, String string, File file) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            FastaOutput_Procedure_Int.writeFile(bufferedOutputStream, string, arrby, n, n2);
            ((OutputStream)bufferedOutputStream).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static final void divideScoresByLengths(Node node) {
        while (node != null) {
            node.score /= node.xE - node.xS + 1;
            node.n.score = node.score;
            node = node.p;
        }
    }

    public static final Generator convertNodeChainToEdgeList(final Node node) {
        return new Generator(){
            Node n;
            {
                this.n = node;
            }

            public Object gen() {
                if (this.n != null) {
                    PolygonFiller.Node node2 = new PolygonFiller.Node(this.n.xS, this.n.n.xS, this.n.n.xE, this.n.score);
                    this.n = this.n.p;
                    return node2;
                }
                return null;
            }
        };
    }

    public static void closeGapsEliminateSpan(Iterator iterator, PrimeConstraints primeConstraints, int n, int n2, int n3, CloseSpan closeSpan) {
        if (!iterator.hasNext()) {
            return;
        }
        PolygonFiller.Node node = (PolygonFiller.Node)iterator.next();
        int n4 = node.x + node.yMax - node.y;
        int n5 = node.yMax;
        while (iterator.hasNext()) {
            node = (PolygonFiller.Node)iterator.next();
            Chains.doGap(primeConstraints, n, n2, n4, n5, node.x, node.y, n3, closeSpan);
            n4 = node.x + node.yMax - node.y;
            n5 = node.yMax;
        }
    }

    static void doGap(PrimeConstraints primeConstraints, int n, int n2, int n3, int n4, int n5, int n6, int n7, CloseSpan closeSpan) {
        if (n5 - n3 - 1 > n7) {
            closeSpan.pro(primeConstraints, n, n2, n3, n4, n5, n6);
        } else if (n6 - n4 - 1 > n7) {
            closeSpan.pro(primeConstraints, n2, n, n4, n3, n6, n5);
        }
    }

    static CloseSpan getMinimumPath(final List<List<Object>> list, final int n, final Aligner aligner, final byte[] arrby, final byte[] arrby2) {
        return new CloseSpan(){

            public void pro(PrimeConstraints primeConstraints, int n7, int n2, int n3, int n4, int n5, int n6) {
                ++noOfLongGapOptimisations;
                if (n7 < n2) {
                    list.add(Chains.getMinimumPath(primeConstraints, n7, n2, n3, n4, n5, n6, n, aligner, arrby, arrby2));
                } else {
                    list.add(Chains.getMinimumPath(primeConstraints, n7, n2, n3, n4, n5, n6, n, aligner, arrby2, arrby));
                }
            }
        };
    }

    static CloseSpan fillInPath(final Iterator iterator) {
        return new CloseSpan(){

            public void pro(PrimeConstraints primeConstraints, int n, int n2, int n3, int n4, int n5, int n6) {
                Chains.fillInPath(primeConstraints, (List)iterator.next());
            }
        };
    }

    static int partitionSequences(PrimeConstraints primeConstraints, int n, int n2, int n3, int n4, int n5, int n6, Aligner aligner, int n7, byte[] arrby, byte[] arrby2) {
        if (n7 * 2 >= n6 - n4) {
            throw new IllegalStateException(n3 + " " + n5 + " " + n4 + " " + n6 + " " + n7);
        }
        List list = Chains.localAlignmentsInGaps(Generators.constant(null), aligner, 1, n7 * 2, n3 + 1, n4 + 1, n5 - n3, n6 - n4, arrby, arrby2);
        Node node = Chains.convertEdgeListToNodeChain(Generators.iteratorGenerator(list.iterator()));
        node = primeConstraints.filterByConstraints(n, n2, node);
        node = Chains.sortOppositeChain(node);
        node = primeConstraints.filterByConstraints(n2, n, node);
        Chains.multiplyScoresByLengths(node);
        node = Chains.getHighestChain(node);
        int n8 = (n6 - n4) / 2 + n4;
        int n9 = n3;
        int n10 = n5;
        while (node != null) {
            if (node.xE >= n8) {
                if (node.xS <= n8) {
                    n10 = n9 = node.n.xS + n8 - node.xS;
                    break;
                }
                n10 = node.n.xS;
                break;
            }
            n9 = node.n.xE;
            node = node.p;
        }
        return n9 + (n10 - n9) / 2 - n3;
    }

    static int[] drawInPoints(PrimeConstraints primeConstraints, int n, int n2, int n3, int n4, int n5, int n6, int n7, Aligner aligner, byte[] arrby, byte[] arrby2) {
        int n8;
        int n9;
        int n10;
        int n11;
        int n12 = n5 - n3;
        if (n12 >= n7 * 2) {
            n10 = n7;
            n9 = n7;
        } else {
            n10 = Chains.partitionSequences(primeConstraints, n, n2, n3, n4, n5, n6, aligner, n7, arrby, arrby2);
            n9 = n12 - n10;
        }
        int n13 = n6 - n4;
        if (n13 >= n7 * 2) {
            n8 = n7;
            n11 = n7;
        } else {
            n8 = Chains.partitionSequences(primeConstraints, n2, n, n4, n3, n6, n5, aligner, n7, arrby2, arrby);
            n11 = n13 - n8;
        }
        PrimeConstraints.ConstraintNode constraintNode = PrimeConstraints.getPrimeConstraint(primeConstraints.primeConstraints[n][n2], n3 + n10);
        PrimeConstraints.ConstraintNode constraintNode2 = PrimeConstraints.getPrimeConstraint(primeConstraints.primeConstraints[n2][n], n4 + n8);
        int n14 = constraintNode.y + (constraintNode.maxOffset != Integer.MIN_VALUE ? constraintNode.maxOffset : -1);
        int n15 = constraintNode2.y + (constraintNode2.maxOffset != Integer.MIN_VALUE ? constraintNode2.maxOffset : -1);
        if (n3 + n10 + n14 < n4 + n8 + n15) {
            n3 += n10;
            n4 = n14 <= n4 + n8 ? n14 : n4 + n8;
        } else {
            n4 += n8;
            n3 = n15 <= n3 + n10 ? n15 : n3 + n10;
        }
        constraintNode = PrimeConstraints.getRightMostPointAffectedByConstraint(primeConstraints.primeConstraints[n2][n], n5 - n9);
        constraintNode2 = PrimeConstraints.getRightMostPointAffectedByConstraint(primeConstraints.primeConstraints[n][n2], n6 - n11);
        n14 = constraintNode.x + (constraintNode.maxOffset != Integer.MIN_VALUE ? constraintNode.maxOffset : 0);
        n15 = constraintNode2.x + (constraintNode2.maxOffset != Integer.MIN_VALUE ? constraintNode2.maxOffset : 0);
        if (n5 - n9 + n14 >= n6 - n11 + n15) {
            n5 -= n9;
            n6 = n14 >= n6 - n11 ? n14 : n6 - n11;
        } else {
            n6 -= n11;
            n5 = n15 >= n5 - n9 ? n15 : n5 - n9;
        }
        return new int[]{n3, n4, n5, n6};
    }

    static List<Object> getMinimumPath(PrimeConstraints primeConstraints, int n, int n2, int n3, int n4, int n5, int n6, int n7, Aligner aligner, byte[] arrby, byte[] arrby2) {
        int[] arrn = Chains.drawInPoints(primeConstraints, n, n2, n3, n4, n5 - 1, n6 - 1, n7, aligner, arrby, arrby2);
        n3 = arrn[0];
        n4 = arrn[1];
        n5 = arrn[2];
        n6 = arrn[3];
        LinkedList<Object> linkedList = new LinkedList<Object>();
        Chains.getMinimumPath(primeConstraints, n, n2, n3 + 1, n5, n6, linkedList);
        LinkedList<Object> linkedList2 = new LinkedList<Object>();
        Chains.getMinimumPath(primeConstraints, n2, n, n4 + 1, n6, n5, linkedList2);
        if (linkedList2.size() < linkedList.size()) {
            linkedList2.add(0, new int[]{n2, n, n4, n3});
            return linkedList2;
        }
        linkedList.add(0, new int[]{n, n2, n3, n4});
        return linkedList;
    }

    static void getMinimumPath(PrimeConstraints primeConstraints, int n, int n2, int n3, int n4, int n5, List<Object> list) {
        PrimeConstraints.ConstraintNode constraintNode = PrimeConstraints.getPrimeConstraint(primeConstraints.primeConstraints[n][n2], n3);
        if (constraintNode.y > n5) {
            list.add(new Integer(n5));
            if (n3 > n4) {
                return;
            }
            Chains.getMinimumPath(primeConstraints, n2, n, n5 + 1, n5, n4, list);
        } else {
            list.add(new Integer(constraintNode.y - 1));
            Chains.getMinimumPath(primeConstraints, n2, n, constraintNode.y, n5, n4, list);
        }
    }

    static void fillInPath(PrimeConstraints primeConstraints, List list) {
        Iterator iterator = list.iterator();
        int[] arrn = (int[])iterator.next();
        int n = arrn[0];
        int n2 = arrn[1];
        int n3 = arrn[2];
        int n4 = arrn[3];
        primeConstraints.updatePrimeConstraints(n, n2, n3, n4 + 1, Integer.MIN_VALUE);
        if (iterator.hasNext()) {
            Chains.fillInPath(primeConstraints, iterator, n, n2, n3 + 1);
        }
    }

    static void fillInPath(PrimeConstraints primeConstraints, Iterator iterator, int n, int n2, int n3) {
        int n4 = (Integer)iterator.next();
        primeConstraints.updatePrimeConstraints(n2, n, n4, n3, Integer.MIN_VALUE);
        if (iterator.hasNext()) {
            Chains.fillInPath(primeConstraints, iterator, n2, n, n4 + 1);
        }
    }

    public static void transformConstraintsAndCloseLargeGaps(PrimeConstraints primeConstraints, List[][] arrlist, int n, int n2, int n3, Aligner aligner, byte[][] arrby, int[][] arrn) {
        if (n3 * 2 > n2) {
            throw new IllegalStateException();
        }
        List[][] arrlist2 = new List[primeConstraints.sequenceNumber][primeConstraints.sequenceNumber];
        int[][] arrn2 = arrn;
        int n4 = arrn2.length;
        for (int i = 0; i < n4; ++i) {
            int[] arrn3 = arrn2[i];
            int n5 = arrn3[0];
            int n6 = arrn3[1];
            arrlist2[n5][n6] = new LinkedList();
            CloseSpan closeSpan = Chains.getMinimumPath(arrlist2[n5][n6], n3, aligner, arrby[n5], arrby[n6]);
            Chains.closeGapsEliminateSpan(arrlist[n5][n6].iterator(), primeConstraints, n5, n6, n2, closeSpan);
            closeSpan = Chains.fillInPath(arrlist2[n5][n6].iterator());
            Chains.closeGapsEliminateSpan(arrlist[n5][n6].iterator(), primeConstraints, n5, n6, n2, closeSpan);
        }
        Chains.transformConstraints(n, primeConstraints);
        for (int i = 0; i < primeConstraints.sequenceNumber; ++i) {
            for (n4 = i + 1; n4 < primeConstraints.sequenceNumber; ++n4) {
                CloseSpan closeSpan = Chains.fillInPath(arrlist2[i][n4].iterator());
                Chains.closeGapsEliminateSpan(arrlist[i][n4].iterator(), primeConstraints, i, n4, n2, closeSpan);
            }
        }
    }
    public static interface CloseSpan {
        public void pro(PrimeConstraints var1, int var2, int var3, int var4, int var5, int var6, int var7);
    }
    public static interface LocalAligner {
        public List<List> align(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6);
    }
    public static interface Aligner {
        public Iterator align(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6);
    }
    static final class EdgeNode {
        int xMin;
        int yMin;
        int yMax;
        long score;
        EdgeNode eN;

        EdgeNode(int n, int n2, int n3, long l, EdgeNode edgeNode) {
            this.xMin = n;
            this.yMin = n2;
            this.yMax = n3;
            this.score = l;
            this.eN = edgeNode;
        }
    }
    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class PrimeConstraints {
        public final SortedSet[][] primeConstraints;
        final int sequenceNumber;
        static final ConstraintNode cN = new ConstraintNode(0, 0, Integer.MIN_VALUE);
        private static final int COORDINATE_MIN_VALUE = -100000;
        static int noOfConstraintsClipped = 0;

        public PrimeConstraints(int n) {
            this.sequenceNumber = n;
            this.primeConstraints = new SortedSet[n][n];
            ConstraintNode constraintNode = new ConstraintNode(-100000, -100000, Integer.MIN_VALUE);
            ConstraintNode constraintNode2 = new ConstraintNode(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == j) continue;
                    TreeSet<ConstraintNode> treeSet = new TreeSet<ConstraintNode>();
                    treeSet.add(constraintNode);
                    treeSet.add(constraintNode2);
                    this.primeConstraints[i][j] = treeSet;
                }
            }
        }

        public final Node filterByConstraints(int n, int n2, Node node) {
            ConstraintNode.compareX = true;
            node = PrimeConstraints.filterByConstraints(node, (List)IterationTools.append(this.primeConstraints[n][n2].iterator(), new LinkedList()));
            return node;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        static final Node filterByConstraints(Node var0, List var1_1) {
            var2_2 = var1_1.listIterator();
            if (!var2_2.hasNext()) {
                return var0;
            }
            var3_3 = (ConstraintNode)var2_2.next();
            var4_4 = new Node();
            var4_4.p = var0;
            var5_5 = var4_4;
            while (var0 != null) {
                block11: {
                    while (var0.xS > (var3_3.maxOffset != Integer.MIN_VALUE ? var3_3.x + var3_3.maxOffset : var3_3.x)) {
                        if (var2_2.hasNext() == false) return var5_5.p;
                        var3_3 = (ConstraintNode)var2_2.next();
                    }
                    var6_6 = 0;
                    while (var0.xE >= var3_3.x) {
                        block12: {
                            if (var3_3.maxOffset != Integer.MIN_VALUE) break block12;
                            if (var0.xS - var0.n.xS <= var3_3.x - var3_3.y) {
                                PrimeConstraints.split(var0, var3_3.x);
                                var4_4 = PrimeConstraints.clip(var4_4, var0, var3_3.y);
                                var0 = var4_4.p;
                                break block11;
                            }
                            ** GOTO lbl-1000
                        }
                        if (var0.xS - var0.n.xS < var3_3.x - var3_3.y) {
                            PrimeConstraints.split(var0, var3_3.x + var3_3.maxOffset);
                            var4_4 = PrimeConstraints.clip(var4_4, var0, var3_3.y);
                            var0 = var4_4.p;
                        } else lbl-1000:
                        // 2 sources
                        {
                            if (var2_2.hasNext()) {
                                var3_3 = (ConstraintNode)var2_2.next();
                                ++var6_6;
                                continue;
                            }
                            var4_4 = var0;
                            var0 = var0.p;
                        }
                        break block11;
                    }
                    if (var0.n.xE >= var3_3.y) {
                        var4_4 = PrimeConstraints.clip(var4_4, var0, var3_3.y);
                        var0 = var4_4.p;
                    } else {
                        var4_4 = var0;
                        var0 = var0.p;
                    }
                }
                if (var6_6 <= 0) continue;
                while (var6_6-- >= 0) {
                    var2_2.previous();
                }
                var3_3 = (ConstraintNode)var2_2.next();
            }
            return var5_5.p;
        }

        static final Node clip(Node node, Node node2, int n) {
            ++noOfConstraintsClipped;
            Node node3 = node2.n;
            if (node3.xS >= n) {
                node.p = node2.p;
                return node;
            }
            node3.xE = n - 1;
            node2.xE = node2.xS + (node3.xE - node3.xS);
            return node2;
        }

        static final void split(Node node, int n) {
            if (node.xE > n) {
                Node node2;
                Node node3 = new Node(n + 1, node.xE, node.score, null, null);
                int n2 = node.n.xE - (node.xE - n);
                node3.n = node2 = new Node(n2 + 1, node.n.xE, node.n.score, node3, null);
                node.xE = n;
                node.n.xE = n2;
                Chains.insert(node3, node);
            }
        }

        public final Generator convertPrimeConstraintsToEdgeList(int n, int n2, boolean bl) {
            SortedSet<ConstraintNode> sortedSet = this.primeConstraints[n][n2];
            ConstraintNode.compareX = false;
            PrimeConstraints.cN.y = -99999;
            sortedSet = sortedSet.tailSet(cN);
            ConstraintNode constraintNode = new ConstraintNode(0, Integer.MAX_VALUE, 0);
            sortedSet = sortedSet.headSet(constraintNode);
            return bl ? PrimeConstraints.convertPrimeConstraintsToEdgeList_ExcludeLessThans(sortedSet.iterator()) : PrimeConstraints.convertPrimeConstraintsToEdgeList(sortedSet.iterator());
        }

        private static final Generator convertPrimeConstraintsToEdgeList(final Iterator<ConstraintNode> iterator) {
            return new Generator(){

                public final Object gen() {
                    if (iterator.hasNext()) {
                        ConstraintNode constraintNode = (ConstraintNode)iterator.next();
                        if (constraintNode.maxOffset != Integer.MIN_VALUE) {
                            return new PolygonFiller.Node(constraintNode.x, constraintNode.y, constraintNode.y + constraintNode.maxOffset, 1);
                        }
                        return new PolygonFiller.Node(constraintNode.x, constraintNode.y, constraintNode.y, 0);
                    }
                    return null;
                }
            };
        }

        private static final Generator convertPrimeConstraintsToEdgeList_ExcludeLessThans(final Iterator<ConstraintNode> iterator) {
            return new Generator(){

                public final Object gen() {
                    if (iterator.hasNext()) {
                        ConstraintNode constraintNode = (ConstraintNode)iterator.next();
                        if (constraintNode.maxOffset != Integer.MIN_VALUE) {
                            return new PolygonFiller.Node(constraintNode.x, constraintNode.y, constraintNode.y + constraintNode.maxOffset, 1);
                        }
                        return this.gen();
                    }
                    return null;
                }
            };
        }

        public final void updatePrimeConstraints(int n, int n2, Node node) {
            while (node != null) {
                this.updatePrimeConstraints(n, n2, node.xS, node.n.xS, node.xE - node.xS);
                node = node.p;
            }
        }

        public final void updatePrimeConstraints(int n, int n2, int n3, int n4, int n5) {
            boolean bl;
            if (n5 == Integer.MIN_VALUE) {
                bl = true;
                n5 = 0;
            } else {
                bl = false;
            }
            for (int i = 0; i < this.sequenceNumber; ++i) {
                if (i == n) continue;
                int n6 = 0;
                int n7 = 0;
                while (n6 <= n5) {
                    boolean bl2;
                    int n8;
                    ConstraintNode constraintNode;
                    if (i != n2) {
                        constraintNode = PrimeConstraints.getPrimeConstraint(this.primeConstraints[n2][i], n4 + n6);
                        if (constraintNode.maxOffset != Integer.MIN_VALUE && constraintNode.x <= n4 + n5) {
                            bl2 = true;
                            if (constraintNode.x > n4 + n6) {
                                n6 = constraintNode.x - n4;
                                n8 = constraintNode.y;
                            } else {
                                n8 = constraintNode.y - constraintNode.x + n4 + n6;
                            }
                            n7 = constraintNode.x + constraintNode.maxOffset < n4 + n5 ? constraintNode.x + constraintNode.maxOffset - n4 : n5;
                        } else {
                            bl2 = false;
                            n8 = constraintNode.y;
                            n7 = constraintNode.x < n4 + n5 ? constraintNode.x - n4 : n5;
                            n6 = n7;
                        }
                    } else {
                        n8 = n4 + n6;
                        bl2 = true;
                        n7 = n5;
                    }
                    constraintNode = PrimeConstraints.getPrimeConstraint(this.primeConstraints[n][i], n3 + n6);
                    if (bl) {
                        bl2 = false;
                    }
                    do {
                        int n9;
                        int n10;
                        if ((n9 = constraintNode.x < n3 + n6 ? constraintNode.y - constraintNode.x + n3 + n6 : constraintNode.y) > n8 || n9 == n8 && !bl2 && constraintNode.maxOffset != Integer.MIN_VALUE && constraintNode.x - constraintNode.y == n3 + n6 - n8) {
                            int n11;
                            do {
                                if (constraintNode.maxOffset != Integer.MIN_VALUE) {
                                    if (constraintNode.x + constraintNode.maxOffset >= n3 + n7) {
                                        n10 = n7;
                                        break;
                                    }
                                    n10 = constraintNode.x + constraintNode.maxOffset - n3;
                                } else {
                                    if (constraintNode.x >= n3 + n7) {
                                        n10 = n7;
                                        break;
                                    }
                                    n10 = constraintNode.x - n3;
                                }
                                n11 = n3 + n10 + 1;
                                constraintNode = PrimeConstraints.getPrimeConstraint(this.primeConstraints[n][i], n11);
                            } while ((n9 = constraintNode.x < n11 ? constraintNode.y - constraintNode.x + n11 : constraintNode.y) >= n8 + n10 + 1 - n6);
                            for (n11 = 0; n11 < this.sequenceNumber; ++n11) {
                                if (n11 == n2 || n11 == i) continue;
                                int n12 = n10;
                                while (n12 >= n6) {
                                    int n13;
                                    boolean bl3;
                                    int n14;
                                    ConstraintNode constraintNode2;
                                    if (n11 != n) {
                                        constraintNode2 = PrimeConstraints.getRightMostPointAffectedByConstraint(this.primeConstraints[n11][n], n3 + n12);
                                        if (constraintNode2.maxOffset != Integer.MIN_VALUE) {
                                            if (constraintNode2.y + constraintNode2.maxOffset >= n3 + n6) {
                                                bl3 = true;
                                                if (constraintNode2.y + constraintNode2.maxOffset < n3 + n12) {
                                                    n12 = constraintNode2.y + constraintNode2.maxOffset - n3;
                                                    n14 = constraintNode2.x + constraintNode2.maxOffset;
                                                } else {
                                                    n14 = constraintNode2.x - constraintNode2.y + n3 + n12;
                                                }
                                                n13 = constraintNode2.y > n3 + n6 ? constraintNode2.y - n3 : n6;
                                                n14 += n13 - n12;
                                            } else {
                                                bl3 = false;
                                                n14 = constraintNode2.x + constraintNode2.maxOffset;
                                                n12 = constraintNode2.y + constraintNode2.maxOffset >= n3 + n6 ? constraintNode2.y + constraintNode2.maxOffset - n3 : n6;
                                                n13 = n12;
                                            }
                                        } else {
                                            bl3 = false;
                                            n14 = constraintNode2.x;
                                            n12 = constraintNode2.y >= n3 + n6 ? constraintNode2.y - n3 : n6;
                                            n13 = n12;
                                        }
                                    } else {
                                        n14 = n3 + n6;
                                        bl3 = true;
                                        n13 = n6;
                                    }
                                    constraintNode2 = PrimeConstraints.getPrimeConstraint(this.primeConstraints[n11][i], n14);
                                    int n15 = n13;
                                    boolean bl4 = bl3 && bl2;
                                    block6: do {
                                        int n16 = constraintNode2.x < n14 ? constraintNode2.y - constraintNode2.x + n14 : constraintNode2.y;
                                        int n17 = n8 + n15 - n6;
                                        if (!bl4) {
                                            if (n16 <= n17 && (n16 != n17 || constraintNode2.maxOffset == Integer.MIN_VALUE || n14 - n17 != constraintNode2.x - constraintNode2.y)) break;
                                            this.updatePrimeConstraints_LessThan(this.primeConstraints[n11][i], new ConstraintNode(n14, n17, Integer.MIN_VALUE));
                                            break;
                                        }
                                        if (n16 > n17) {
                                            int n18 = n8 + n12 - n6;
                                            do {
                                                if (n16 > n18) {
                                                    this.updatePrimeConstraints_LessThanOrEqual(this.primeConstraints[n11][i], new ConstraintNode(n14, n17, n12 - n15));
                                                    break block6;
                                                }
                                                if (constraintNode2.x - constraintNode2.y >= n14 - n17) break;
                                                constraintNode2 = constraintNode2.maxOffset == Integer.MIN_VALUE ? PrimeConstraints.getPrimeConstraint(this.primeConstraints[n11][i], constraintNode2.x + 1) : PrimeConstraints.getPrimeConstraint(this.primeConstraints[n11][i], constraintNode2.x + constraintNode2.maxOffset + 1);
                                                n16 = constraintNode2.y;
                                            } while (true);
                                            this.updatePrimeConstraints_LessThanOrEqual(this.primeConstraints[n11][i], new ConstraintNode(n14, n17, n16 - n17 - 1));
                                        }
                                        if (constraintNode2.maxOffset != Integer.MIN_VALUE) {
                                            if (constraintNode2.x + constraintNode2.maxOffset >= n14 + (n12 - n15)) break;
                                            n17 = constraintNode2.x + constraintNode2.maxOffset - n14 + 1;
                                            n15 += n17;
                                            n14 += n17;
                                        } else {
                                            if (constraintNode2.x >= n14 + (n12 - n15)) break;
                                            n17 = constraintNode2.x - n14 + 1;
                                            n15 += n17;
                                            n14 += n17;
                                        }
                                        constraintNode2 = PrimeConstraints.getPrimeConstraint(this.primeConstraints[n11][i], n14);
                                    } while (true);
                                    n12 = n13 - 1;
                                }
                            }
                        }
                        if (constraintNode.maxOffset != Integer.MIN_VALUE) {
                            if (constraintNode.x + constraintNode.maxOffset >= n3 + n7) break;
                            n10 = constraintNode.x + constraintNode.maxOffset - n3 + 1;
                            n8 += n10 - n6;
                            n6 = n10;
                        } else {
                            if (constraintNode.x >= n3 + n7) break;
                            n10 = constraintNode.x - n3 + 1;
                            n8 += n10 - n6;
                            n6 = n10;
                        }
                        constraintNode = PrimeConstraints.getPrimeConstraint(this.primeConstraints[n][i], n3 + n6);
                    } while (true);
                    n6 = n7 + 1;
                }
            }
        }

        final void updatePrimeConstraints_LessThan(SortedSet<ConstraintNode> sortedSet, ConstraintNode constraintNode) {
            ConstraintNode.compareX = false;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.headSet(constraintNode);
            ConstraintNode constraintNode2 = sortedSet2.last();
            int n = constraintNode.x;
            if (constraintNode2.maxOffset != Integer.MIN_VALUE && constraintNode2.y + constraintNode2.maxOffset >= constraintNode.y) {
                if (constraintNode2.x + constraintNode2.maxOffset > n) {
                    int n2 = n + 1;
                    int n3 = n2 - constraintNode2.x;
                    ConstraintNode constraintNode3 = new ConstraintNode(n2, constraintNode2.y + n3, constraintNode2.maxOffset - n3);
                    constraintNode2.maxOffset = constraintNode.y - constraintNode2.y - 1;
                    sortedSet.add(constraintNode);
                    sortedSet.add(constraintNode3);
                    return;
                }
                constraintNode2.maxOffset = constraintNode.y - constraintNode2.y - 1;
            }
            this.updatePrimeConstraints_LessThan(sortedSet, constraintNode, n);
        }

        final void updatePrimeConstraints_LessThanOrEqual(SortedSet<ConstraintNode> sortedSet, ConstraintNode constraintNode) {
            ConstraintNode.compareX = false;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.headSet(constraintNode);
            ConstraintNode constraintNode2 = sortedSet2.last();
            int n = constraintNode.x;
            n += constraintNode.maxOffset;
            int n2 = constraintNode.x - constraintNode.y;
            if (constraintNode2.maxOffset != Integer.MIN_VALUE) {
                int n3 = constraintNode2.y + constraintNode2.maxOffset;
                if (n3 >= constraintNode.y) {
                    if (constraintNode2.x + constraintNode2.maxOffset > n) {
                        if (constraintNode2.x - constraintNode2.y == n2) {
                            return;
                        }
                        int n4 = n + 1;
                        int n5 = n4 - constraintNode2.x;
                        ConstraintNode constraintNode3 = new ConstraintNode(n4, constraintNode2.y + n5, constraintNode2.maxOffset - n5);
                        constraintNode2.maxOffset = constraintNode.y - constraintNode2.y - 1;
                        sortedSet.add(constraintNode);
                        sortedSet.add(constraintNode3);
                        return;
                    }
                    if (constraintNode2.x - constraintNode2.y == n2) {
                        this.updatePrimeConstraints_LessThanOrEqual2(sortedSet, constraintNode2, n, n2);
                        return;
                    }
                    constraintNode2.maxOffset = constraintNode.y - constraintNode2.y - 1;
                    this.updatePrimeConstraints_LessThanOrEqual(sortedSet, constraintNode, n, n2);
                    return;
                }
                if (n3 + 1 == constraintNode.y && constraintNode2.x - constraintNode2.y == n2) {
                    this.updatePrimeConstraints_LessThanOrEqual2(sortedSet, constraintNode2, n, n2);
                    return;
                }
            }
            this.updatePrimeConstraints_LessThanOrEqual(sortedSet, constraintNode, n, n2);
        }

        final void updatePrimeConstraints_LessThan(SortedSet<ConstraintNode> sortedSet, ConstraintNode constraintNode, int n) {
            ConstraintNode.compareX = false;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.tailSet(constraintNode);
            ConstraintNode constraintNode2 = sortedSet2.first();
            if (constraintNode2.x <= n) {
                int n2 = constraintNode2.x;
                if (constraintNode2.maxOffset != Integer.MIN_VALUE) {
                    n2 += constraintNode2.maxOffset;
                }
                if (n2 > n) {
                    int n3 = n + 1;
                    constraintNode2.y = constraintNode2.y - constraintNode2.x + n3;
                    constraintNode2.x = n3;
                    constraintNode2.maxOffset = n2 - n3;
                    sortedSet.add(constraintNode);
                    return;
                }
                PrimeConstraints.cN.y = constraintNode2.y + 1;
                sortedSet2 = sortedSet2.tailSet(cN);
                ConstraintNode constraintNode3 = sortedSet2.first();
                while (constraintNode3.x <= n) {
                    n2 = constraintNode3.x;
                    if (constraintNode3.maxOffset != Integer.MIN_VALUE) {
                        n2 += constraintNode3.maxOffset;
                    }
                    if (n2 > n) {
                        int n4 = n + 1;
                        constraintNode3.y = constraintNode3.y - constraintNode3.x + n4;
                        constraintNode3.x = n4;
                        constraintNode3.maxOffset = n2 - n4;
                        break;
                    }
                    sortedSet2.remove(constraintNode3);
                    constraintNode3 = sortedSet2.first();
                }
                constraintNode2.x = constraintNode.x;
                constraintNode2.y = constraintNode.y;
                constraintNode2.maxOffset = constraintNode.maxOffset;
                return;
            }
            sortedSet.add(constraintNode);
        }

        final void updatePrimeConstraints_LessThanOrEqual(SortedSet<ConstraintNode> sortedSet, ConstraintNode constraintNode, int n, int n2) {
            ConstraintNode.compareX = false;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.tailSet(constraintNode);
            ConstraintNode constraintNode2 = sortedSet2.first();
            if (constraintNode2.x <= n) {
                int n3 = constraintNode2.x;
                if (constraintNode2.maxOffset != Integer.MIN_VALUE) {
                    n3 += constraintNode2.maxOffset;
                }
                if (n3 > n) {
                    if (constraintNode2.x - constraintNode2.y == n2) {
                        constraintNode2.maxOffset += constraintNode2.x - constraintNode.x;
                        constraintNode2.x = constraintNode.x;
                        constraintNode2.y = constraintNode.y;
                        return;
                    }
                    int n4 = n + 1;
                    constraintNode2.y = constraintNode2.y - constraintNode2.x + n4;
                    constraintNode2.x = n4;
                    constraintNode2.maxOffset = n3 - n4;
                    sortedSet.add(constraintNode);
                    return;
                }
                PrimeConstraints.cN.y = constraintNode2.y + 1;
                sortedSet2 = sortedSet2.tailSet(cN);
                ConstraintNode constraintNode3 = sortedSet2.first();
                while (constraintNode3.x <= n) {
                    n3 = constraintNode3.x;
                    if (constraintNode3.maxOffset != Integer.MIN_VALUE) {
                        n3 += constraintNode3.maxOffset;
                    }
                    if (n3 > n) {
                        if (constraintNode3.x - constraintNode3.y == n2) {
                            sortedSet2.remove(constraintNode2);
                            constraintNode3.maxOffset += constraintNode3.x - constraintNode.x;
                            constraintNode3.x = constraintNode.x;
                            constraintNode3.y = constraintNode.y;
                            return;
                        }
                        int n5 = n + 1;
                        constraintNode3.y = constraintNode3.y - constraintNode3.x + n5;
                        constraintNode3.x = n5;
                        constraintNode3.maxOffset = n3 - n5;
                        break;
                    }
                    sortedSet2.remove(constraintNode3);
                    constraintNode3 = sortedSet2.first();
                }
                if (constraintNode3.x - 1 == n && constraintNode3.x - constraintNode3.y == n2 && constraintNode3.maxOffset != Integer.MIN_VALUE) {
                    sortedSet2.remove(constraintNode3);
                    constraintNode2.x = constraintNode.x;
                    constraintNode2.y = constraintNode.y;
                    constraintNode2.maxOffset = constraintNode3.x + constraintNode3.maxOffset - constraintNode.x;
                    return;
                }
                constraintNode2.x = constraintNode.x;
                constraintNode2.y = constraintNode.y;
                constraintNode2.maxOffset = constraintNode.maxOffset;
                return;
            }
            if (constraintNode2.x - 1 == n && constraintNode2.x - constraintNode2.y == n2 && constraintNode2.maxOffset != Integer.MIN_VALUE) {
                constraintNode2.maxOffset += constraintNode2.x - constraintNode.x;
                constraintNode2.x = constraintNode.x;
                constraintNode2.y = constraintNode.y;
                return;
            }
            sortedSet.add(constraintNode);
        }

        final void updatePrimeConstraints_LessThanOrEqual2(SortedSet<ConstraintNode> sortedSet, ConstraintNode constraintNode, int n, int n2) {
            PrimeConstraints.cN.y = constraintNode.y + 1;
            ConstraintNode.compareX = false;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.tailSet(cN);
            ConstraintNode constraintNode2 = sortedSet2.first();
            while (constraintNode2.x <= n) {
                int n3 = constraintNode2.x;
                if (constraintNode2.maxOffset != Integer.MIN_VALUE) {
                    n3 += constraintNode2.maxOffset;
                }
                if (n3 > n) {
                    if (constraintNode2.x - constraintNode2.y == n2) {
                        sortedSet2.remove(constraintNode2);
                        constraintNode.maxOffset = n3 - constraintNode.x;
                        return;
                    }
                    int n4 = n + 1;
                    constraintNode2.y = constraintNode2.y - constraintNode2.x + n4;
                    constraintNode2.x = n4;
                    constraintNode2.maxOffset = n3 - n4;
                    break;
                }
                sortedSet2.remove(constraintNode2);
                constraintNode2 = sortedSet2.first();
            }
            if (constraintNode2.x - 1 == n && constraintNode2.x - constraintNode2.y == n2 && constraintNode2.maxOffset != Integer.MIN_VALUE) {
                sortedSet2.remove(constraintNode2);
                constraintNode.maxOffset = constraintNode2.x + constraintNode2.maxOffset - constraintNode.x;
                return;
            }
            constraintNode.maxOffset = n - constraintNode.x;
        }

        static final ConstraintNode getRightMostPointAffectedByConstraint(SortedSet<ConstraintNode> sortedSet, int n) {
            ConstraintNode.compareX = false;
            PrimeConstraints.cN.y = n + 1;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.headSet(cN);
            return (ConstraintNode)sortedSet2.last().clone();
        }

        static final ConstraintNode getPrimeConstraint(SortedSet<ConstraintNode> sortedSet, int n) {
            ConstraintNode.compareX = true;
            PrimeConstraints.cN.x = n;
            SortedSet<ConstraintNode> sortedSet2 = sortedSet.tailSet(cN);
            return (ConstraintNode)sortedSet2.first().clone();
        }
        public static final class ConstraintNode
        implements Comparable,
        Cloneable {
            int x;
            int y;
            int maxOffset;
            public static boolean compareX = true;
            public static final boolean LESS_THAN_OR_EQUAL = true;
            public static final boolean LESS_THAN = false;

            public ConstraintNode(int n, int n2, int n3) {
                this.x = n;
                this.y = n2;
                this.maxOffset = n3;
            }

            public final int compareTo(Object object) {
                ConstraintNode constraintNode = (ConstraintNode)object;
                if (compareX) {
                    return (this.maxOffset != Integer.MIN_VALUE ? this.x + this.maxOffset : this.x) < constraintNode.x ? -1 : (this.x > (constraintNode.maxOffset != Integer.MIN_VALUE ? constraintNode.x + constraintNode.maxOffset : constraintNode.x) ? 1 : 0);
                }
                return this.y < constraintNode.y ? -1 : (this.y > constraintNode.y ? 1 : 0);
            }

            public final boolean equals(Object object) {
                return super.equals(object);
            }

            public final String toString() {
                return " ( " + this.x + " " + this.y + " " + this.maxOffset + " ) ";
            }

            public final Object clone() {
                try {
                    return super.clone();
                }
                catch (CloneNotSupportedException cloneNotSupportedException) {
                    throw new IllegalStateException();
                }
            }
        }

    }
    public static class Node {
        int xS;
        int xE;
        int score;
        Node n;
        Node p;

        Node() {
        }

        Node(int n, int n2, int n3, Node node, Node node2) {
            this.xS = n;
            this.xE = n2;
            this.score = n3;
            this.n = node;
            this.p = node2;
        }

        public String toString() {
            return " ( " + this.xS + " : " + this.xE + " : " + this.score + " , " + this.n.xS + " : " + this.n.xE + " : " + this.n.score + " ) " + (this.p != null ? this.p.toString() : "");
        }
    }
    public static final class CutPointOrdering {
        static final int[][] getSequencePositions(int n, List[][] arrlist) {
            int[][] arrarrn = new int[n][];
            for (int i = 0; i < n; ++i) {
                int n2;
                List list;
                int n3;
                int n4 = 0;
                for (n3 = 0; n3 < i; ++n3) {
                    n4 += arrlist[n3][i].size();
                }
                for (n3 = i + 1; n3 < n; ++n3) {
                    n4 += arrlist[i][n3].size();
                }
                int[] arrn = new int[n4];
                int n5 = 0;
                for (n2 = 0; n2 < i; ++n2) {
                    list = arrlist[n2][i];
                    for (CutPoint cutPoint : list) {
                        arrn[n5++] = cutPoint.y;
                    }
                }
                for (n2 = i + 1; n2 < n; ++n2) {
                    list = arrlist[i][n2];
                    for (CutPoint cutPoint : list) {
                        arrn[n5++] = cutPoint.x;
                    }
                }
                Arrays.sort(arrn);
                n5 = Array.uniq(arrn, arrn);
                int[] arrn2 = new int[n5];
                System.arraycopy(arrn, 0, arrn2, 0, n5);
                arrarrn[i] = arrn2;
            }
            return arrarrn;
        }

        public static final List orderCutPoints(int n, int[][] arrn, int[][] arrn2, List[][] arrlist) {
            int n2;
            int n3 = 0;
            for (int i = 0; i < arrlist.length; ++i) {
                for (n2 = i + 1; n2 < arrlist.length; ++n2) {
                    n3 += arrlist[i][n2].size();
                }
            }
            CPO[] arrcPO = new CPO[n3];
            n2 = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    List list = arrlist[i][j];
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        int n4;
                        CutPoint cutPoint;
                        CPO cPO = new CPO();
                        cPO.cP = cutPoint = (CutPoint)iterator.next();
                        int n5 = arrn2[i][Arrays.binarySearch(arrn[i], cutPoint.x)];
                        if (n5 < (n4 = arrn2[j][Arrays.binarySearch(arrn[j], cutPoint.y)])) {
                            cPO.x = n4;
                            cPO.y = n5;
                        } else {
                            cPO.x = n5;
                            cPO.y = n4;
                        }
                        arrcPO[n2++] = cPO;
                    }
                }
            }
            Arrays.sort(arrcPO, new Comparator(){

                public int compare(Object object, Object object2) {
                    CPO cPO = (CPO)object;
                    CPO cPO2 = (CPO)object2;
                    return cPO.x > cPO2.x ? -1 : (cPO.x < cPO2.x ? 1 : (cPO.y > cPO2.y ? -1 : (cPO.y < cPO2.y ? 1 : 0)));
                }
            });
            LinkedList<CutPoint> linkedList = new LinkedList<CutPoint>();
            for (CPO cPO : arrcPO) {
                linkedList.add(cPO.cP);
            }
            return linkedList;
        }

        public static final List orderCutPoints(PrimeConstraints primeConstraints, List[][] arrlist) {
            int[][] arrn = CutPointOrdering.getSequencePositions(primeConstraints.sequenceNumber, arrlist);
            int[][] arrn2 = CutPointOrdering.dfs(primeConstraints, arrn);
            return CutPointOrdering.orderCutPoints(primeConstraints.sequenceNumber, arrn, arrn2, arrlist);
        }

        public static int[][] dfs(PrimeConstraints primeConstraints, int[][] arrn) {
            int[] arrn2;
            int n;
            int[][] arrarrn = new int[primeConstraints.sequenceNumber][];
            boolean[][] arrarrbl = new boolean[primeConstraints.sequenceNumber][];
            int n2 = 0;
            int n3 = 0;
            for (n = 0; n < primeConstraints.sequenceNumber; ++n) {
                arrn2 = arrn[n];
                arrarrn[n] = new int[arrn2.length];
                arrarrbl[n] = new boolean[arrn2.length];
                n3 += arrn2.length;
            }
            int[] arrn3 = new int[n3 * 3];
            n3 = 1;
            for (n = 0; n < primeConstraints.sequenceNumber; ++n) {
                arrn2 = arrn[n];
                block2: for (int i = 0; i < arrn2.length; ++i) {
                    if (arrarrn[n][i] != 0) continue;
                    int n4 = arrn3[0] = n;
                    int n5 = arrn3[1] = i;
                    arrn3[2] = 0;
                    int n6 = 0;
                    arrarrbl[n4][n5] = true;
                    n2 = 3;
                    block3: do {
                        int n7 = arrn[n4][n5];
                        for (int j = n6; j < primeConstraints.sequenceNumber; ++j) {
                            int n8;
                            if (j != n4) {
                                PrimeConstraints.ConstraintNode constraintNode = PrimeConstraints.getPrimeConstraint(primeConstraints.primeConstraints[n4][j], n7);
                                n8 = constraintNode.maxOffset == Integer.MIN_VALUE ? constraintNode.y : (constraintNode.x <= n7 ? constraintNode.y - constraintNode.x + n7 + 1 : constraintNode.y);
                            } else {
                                n8 = n7 + 1;
                            }
                            n8 = Arrays.binarySearch(arrn[j], n8);
                            if (n8 < 0) {
                                n8 = Math.abs(n8 + 1);
                            }
                            if (n8 >= arrn[j].length || arrarrbl[j][n8]) continue;
                            arrn3[n2 - 1] = j + 1;
                            int n9 = n2++;
                            int n10 = j;
                            arrn3[n9] = n10;
                            n4 = n10;
                            int n11 = n2++;
                            int n12 = n8;
                            arrn3[n11] = n12;
                            n5 = n12;
                            arrn3[n2++] = 0;
                            n6 = 0;
                            arrarrbl[n4][n5] = true;
                            continue block3;
                        }
                        arrarrn[n4][n5] = n3++;
                        if ((n2 -= 3) == 0) continue block2;
                        n4 = arrn3[n2 - 3];
                        n5 = arrn3[n2 - 2];
                        n6 = arrn3[n2 - 1];
                    } while (true);
                }
            }
            return arrarrn;
        }
        private static final class CPO {
            int x;
            int y;
            CutPoint cP;

            private CPO() {
            }
        }

    }
    public static final class CutPointReordering {
        public static final List[][] splitCutPointsBySequence(List list, int n, int n2) {
            List[][] arrlist = new List[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    arrlist[i][j] = new LinkedList();
                    arrlist[j][i] = arrlist[i][j];
                }
            }
            long l = 0L;
            for (CutPoint cutPoint : list) {
                int n3;
                List list2 = arrlist[cutPoint.s1][cutPoint.s2];
                if (list2.size() == 0) {
                    n3 = n2;
                } else {
                    CutPoint cutPoint2 = ((CPO)list2.get((int)(list2.size() - 1))).cP;
                    n3 = cutPoint2.x + cutPoint2.y;
                }
                int n4 = cutPoint.x + cutPoint.y - n3;
                if (n4 < 0) {
                    throw new IllegalStateException(cutPoint + " " + n4 + " " + (list2.size() != 0 ? ((CPO)list2.get((int)(list2.size() - 1))).cP : null) + " " + n3);
                }
                CPO cPO = new CPO();
                cPO.cP = cutPoint;
                cPO.i = l += (long)n4;
                cPO.j = l;
                list2.add(cPO);
            }
            return arrlist;
        }

        public static final List zipCutPointsBySequences(List[][] arrlist, int n) {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    linkedList.addAll(arrlist[i][j]);
                }
            }
            Collections.sort(linkedList, new Comparator(){

                public int compare(Object object, Object object2) {
                    CPO cPO = (CPO)object;
                    CPO cPO2 = (CPO)object2;
                    return cPO.j < cPO2.j ? -1 : (cPO.j > cPO2.j ? 1 : (cPO.i < cPO2.i ? -1 : (cPO.i > cPO2.i ? 1 : 0)));
                }
            });
            ListIterator<CutPoint> listIterator = linkedList.listIterator();
            while (listIterator.hasNext()) {
                CPO cPO = (CPO)listIterator.next();
                listIterator.set(cPO.cP);
            }
            return linkedList;
        }

        public static final void reorder(List list, List list2, int n) {
            CPO cPO;
            Iterator iterator = list.iterator();
            Iterator iterator2 = list2.iterator();
            if (iterator2.hasNext()) {
                cPO = (CPO)iterator2.next();
            } else {
                return;
            }
            while (iterator.hasNext()) {
                CPO cPO2 = (CPO)iterator.next();
                while (cPO.i - (long)n <= cPO2.i) {
                    if (cPO.j >= cPO2.j) {
                        cPO.j = cPO2.j - 1L;
                    }
                    if (iterator2.hasNext()) {
                        cPO = (CPO)iterator2.next();
                        continue;
                    }
                    return;
                }
            }
        }

        public static final List reorder(List list, int n, int[][][] arrn, int[][] arrn2, int n2, int n3) {
            List[][] arrlist = CutPointReordering.splitCutPointsBySequence(list, n, n3);
            for (int i = arrn2.length - 1; i >= 0; --i) {
                int[] arrn3;
                int[] arrn4 = arrn2[i];
                int n4 = arrn4[0];
                int n5 = arrn4[1];
                for (int n6 : arrn3 = arrn[n4][n5]) {
                    CutPointReordering.reorder(arrlist[n4][n5], arrlist[n4][n6], n2);
                    CutPointReordering.reorder(arrlist[n4][n5], arrlist[n5][n6], n2);
                }
            }
            return CutPointReordering.zipCutPointsBySequences(arrlist, n);
        }
        private static final class CPO {
            long i;
            long j;
            CutPoint cP;

            private CPO() {
            }
        }

    }
    public static class CutPoint {
        int x;
        int y;
        int s1;
        int s2;
        int tB;

        public CutPoint(int n, int n2, int n3, int n4, int n5) {
            this.s1 = n;
            this.s2 = n2;
            this.x = n3;
            this.y = n4;
            this.tB = n5;
        }

        public String toString() {
            return " ( " + this.x + " " + this.y + " " + this.s1 + " " + this.s2 + " " + this.tB + " ) ";
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.SkipList;
import bp.common.ds.wrappers.MutableInteger;
import bp.common.fp.Generator;
import bp.common.fp.Generator_Int;
import bp.common.fp.Predicate_Double_2Args;
import java.util.LinkedList;

public class DripAligner
implements Generator {
    static final GenerateNode generateNode_Column = new GenerateNode(){

        public Node gen(int n, int n2, double d, Node node) {
            return new Node(n2, n, d, node);
        }
    };
    static final GenerateNode generateNode_Row = new GenerateNode(){

        public Node gen(int n, int n2, double d, Node node) {
            return new Node(n, n2, d, node);
        }
    };
    static final GetFocusCoordinate getFocusCoordinate_Column = new GetFocusCoordinate(){

        public final int get(Node node) {
            return node.y;
        }
    };
    static final GetFocusCoordinate getFocusCoordinate_Row = new GetFocusCoordinate(){

        public final int get(Node node) {
            return node.x;
        }
    };
    static final int ROWBIT = 1;
    static final int COLUMNBIT = 2;
    static final int SILENTBIT = 4;
    final SkipList row = new SkipList();
    final SkipList column = new SkipList();
    Generator_Int rowLineProcedure;
    Generator_Int columnLineProcedure;
    int rowOffset = 0;
    int columnOffset = 0;
    double pScore;

    static final void doLine(SkipList skipList, int[] arrn, int n, int n2, GenerateNode generateNode, GetFocusCoordinate getFocusCoordinate, Add add, Predicate_Double_2Args predicate_Double_2Args) {
        int n3;
        Node node;
        LinkedList<Node> linkedList = new LinkedList<Node>();
        for (n3 = 0; n3 < arrn.length; n3 += 2) {
            if (arrn[n3] < n2) continue;
            node = (Node)skipList.searchLessThan(arrn[n3]);
            linkedList.add(generateNode.gen(arrn[n3], n, add.fn(node.score, arrn[n3 + 1]), node));
        }
        for (n3 = 0; n3 < linkedList.size(); ++n3) {
            node = (Node)linkedList.get(n3);
            Node node2 = (Node)skipList.searchLessThan(getFocusCoordinate.get(node) + 1);
            if (!predicate_Double_2Args.test(node.score, node2.score)) continue;
            while ((node2 = (Node)skipList.searchGreaterThanOrEqual(getFocusCoordinate.get(node) + 1)) != null && !predicate_Double_2Args.test(node2.score, node.score)) {
                skipList.delete(getFocusCoordinate.get(node2));
            }
            skipList.insert(getFocusCoordinate.get(node), node);
        }
    }

    static final void findAnchor(SkipList skipList, int n, SkipList skipList2, int n2) {
        if (skipList.searchGreaterThanOrEqual(n2 + 1) == null && skipList2.searchGreaterThanOrEqual(n + 1) == null) {
            Node node = (Node)skipList.searchLessThan(n2 + 1);
            if (node.x != n2 || node.y != n) {
                Node node2 = new Node(n2, n, node.score, node);
                node2.z = 4;
                while ((node = (Node)skipList.searchLessThan(n2 + 1)) != null) {
                    skipList.delete(node.x);
                }
                while ((node = (Node)skipList2.searchLessThan(n + 1)) != null) {
                    skipList2.delete(node.y);
                }
                skipList.insert(n2, node2);
                skipList2.insert(n, node2);
            }
        }
    }

    static final Node getAlignment(SkipList skipList, SkipList skipList2) {
        Node node = (Node)skipList.searchLessThan(Integer.MAX_VALUE);
        if ((node = DripAligner.traceBackAndLabel(node, 1, 2)) != null) {
            node = DripAligner.switchBackPointers(node);
            Node node2 = (Node)skipList2.searchLessThan(Integer.MAX_VALUE);
            if ((node2 = DripAligner.traceBackAndLabel(node2, 2, 1)) != null) {
                DripAligner.switchBackPointers(node2);
            }
            return node.n;
        }
        node = (Node)skipList2.searchLessThan(Integer.MAX_VALUE);
        return (node = DripAligner.traceBackAndLabel(node, 2, 1)) != null ? DripAligner.switchBackPointers((Node)node).n : null;
    }

    static final Generator_Int inputLineProcedure(final SkipList skipList, final MutableInteger mutableInteger, final SkipList skipList2, final MutableInteger mutableInteger2, final Generator generator, final GenerateNode generateNode, final GetFocusCoordinate getFocusCoordinate, final Add add, final Predicate_Double_2Args predicate_Double_2Args) {
        return new Generator_Int(){

            public int gen() {
                int[] arrn;
                while ((arrn = (int[])generator.gen()) != null) {
                    DripAligner.doLine(skipList, arrn, mutableInteger.i, mutableInteger2.i, generateNode, getFocusCoordinate, add, predicate_Double_2Args);
                    DripAligner.syncWithOtherLine(skipList, skipList2, mutableInteger.i++, getFocusCoordinate, predicate_Double_2Args);
                }
                return mutableInteger.i;
            }
        };
    }

    static final Node switchBackPointers(Node node) {
        Node node2;
        Node node3 = null;
        do {
            node2 = node.n;
            node.n = node3;
            node3 = node;
        } while ((node = node2) != null);
        return node3;
    }

    static final void syncWithOtherLine(SkipList skipList, SkipList skipList2, int n, GetFocusCoordinate getFocusCoordinate, Predicate_Double_2Args predicate_Double_2Args) {
        Node node = (Node)skipList2.search(n);
        if (node != null) {
            Node node2;
            while ((node2 = (Node)skipList.searchLessThan(getFocusCoordinate.get(node))) != null) {
                skipList.delete(getFocusCoordinate.get(node2));
            }
            while ((node2 = (Node)skipList.searchGreaterThanOrEqual(getFocusCoordinate.get(node) + 1)) != null && !predicate_Double_2Args.test(node2.score, node.score)) {
                skipList.delete(getFocusCoordinate.get(node2));
            }
            skipList.insert(getFocusCoordinate.get(node), node);
        }
    }

    static final Node traceBackAndLabel(Node node, int n, int n2) {
        while (node != null && (node.z & n) == 0) {
            if ((node.z & n2) != 0) {
                return node;
            }
            node.z |= n;
            node = node.n;
        }
        return null;
    }

    public DripAligner(Generator generator, Generator generator2, Add add, Predicate_Double_2Args predicate_Double_2Args, double d) {
        Node node = new Node(-1, -1, d, null);
        this.row.insert(-1, node);
        this.column.insert(-1, node);
        MutableInteger mutableInteger = new MutableInteger();
        MutableInteger mutableInteger2 = new MutableInteger();
        this.rowLineProcedure = DripAligner.inputLineProcedure(this.row, mutableInteger, this.column, mutableInteger2, generator2, generateNode_Row, getFocusCoordinate_Row, add, predicate_Double_2Args);
        this.columnLineProcedure = DripAligner.inputLineProcedure(this.column, mutableInteger2, this.row, mutableInteger, generator, generateNode_Column, getFocusCoordinate_Column, add, predicate_Double_2Args);
        this.pScore = d;
    }

    final Node align() {
        int n = this.rowLineProcedure.gen();
        int n2 = this.columnLineProcedure.gen();
        DripAligner.findAnchor(this.row, n - 1, this.column, n2 - 1);
        return DripAligner.getAlignment(this.row, this.column);
    }

    public final Object gen() {
        Node node = this.align();
        return node != null ? this.translateNodes(node) : null;
    }

    final Generator translateNodes(final Node node) {
        return new Generator(){
            LinkedList<Float> l = new LinkedList();
            Node m = node;
            final Float rowGap = new Float(Float.NEGATIVE_INFINITY);
            final Float columnGap = new Float(Float.POSITIVE_INFINITY);

            public final Object gen() {
                if (this.l.size() != 0) {
                    return this.l.removeFirst();
                }
                if (this.m == null) {
                    return null;
                }
                if ((this.m.z & 4) == 0) {
                    while (DripAligner.this.rowOffset++ < this.m.x) {
                        this.l.add(this.columnGap);
                    }
                    while (DripAligner.this.columnOffset++ < this.m.y) {
                        this.l.add(this.rowGap);
                    }
                    double d = this.m.score - DripAligner.this.pScore;
                    this.l.add(new Float(d));
                    DripAligner.this.pScore = this.m.score;
                }
                while (DripAligner.this.rowOffset <= this.m.x) {
                    ++DripAligner.this.rowOffset;
                    this.l.add(this.columnGap);
                }
                while (DripAligner.this.columnOffset <= this.m.y) {
                    ++DripAligner.this.columnOffset;
                    this.l.add(this.rowGap);
                }
                this.m = this.m.n;
                return this.gen();
            }
        };
    }
    static class Node {
        Node n;
        int x;
        int y;
        int z;
        double score;

        Node(int n, int n2, double d, Node node) {
            this.x = n;
            this.y = n2;
            this.score = d;
            this.n = node;
        }
    }
    static interface GetFocusCoordinate {
        public int get(Node var1);
    }
    static interface GenerateNode {
        public Node gen(int var1, int var2, double var3, Node var5);
    }
    static interface Add {
        public double fn(double var1, int var3);
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.FloatStack;
import bp.common.fp.Function_Index;
import bp.common.fp.Function_Index_3Args;
import bp.common.fp.Procedure_Int;
import bp.common.fp.Procedure_NoArgs;
import bp.common.maths.Maths;
import bp.pecan.Cell;
import bp.pecan.MatrixIterator;
import bp.pecan.PolygonFiller;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public final class ForwardBackwardMatrixIter {
    static final Logger logger = Logger.getLogger(ForwardBackwardMatrixIter.class.getName());
    List<Object[]> fStack = new LinkedList<Object[]>();
    List<Object[]> rStack = new LinkedList<Object[]>();
    boolean holdingFDiagonalEmpty;
    final float[] holdingFDiagonal;
    final float[] fDiag;
    final float[] rDiag;
    final float[] eStates;
    int x0;
    int y0;
    int fDiagC;
    int uptoDiagC;
    final Function_Index polygonGen;
    final List<Object[]> polygonsToMerge;
    final int stateNumber;
    final Cell.CellCalculator computeCellsFLL;
    final Cell.CellCalculator computeCellsBLL;
    final Cell.CellCalculator computeCellsFL;
    final Cell.CellCalculator computeCellsBL;
    final Cell.CellCalculator computeCellsFR;
    final Cell.CellCalculator computeCellsBR;
    final Cell.CellCalculator passCellsF;
    final Cell.CellCalculator passCellsB;
    final Procedure_Int passRunningTotal;
    final Procedure_NoArgs reset;
    final MatrixIterator mI;
    final FloatStack lineStack;
    final int maxDistanceBetweenRescale;

    public ForwardBackwardMatrixIter(Function_Index function_Index, int n, int n2, int n3, int n4, float[] arrf, float[] arrf2, float[] arrf3, float[] arrf4, Cell.CellCalculator cellCalculator, Cell.CellCalculator cellCalculator2, Cell.CellCalculator cellCalculator3, Cell.CellCalculator cellCalculator4, Cell.CellCalculator cellCalculator5, Cell.CellCalculator cellCalculator6, Cell.CellCalculator cellCalculator7, Cell.CellCalculator cellCalculator8, Procedure_Int procedure_Int, MatrixIterator matrixIterator, FloatStack floatStack, int n5, Procedure_NoArgs procedure_NoArgs) {
        this.polygonGen = function_Index;
        this.stateNumber = n;
        this.x0 = n2;
        this.y0 = n3;
        this.uptoDiagC = n2 + n3;
        this.fDiagC = n4;
        this.holdingFDiagonal = arrf;
        this.fDiag = arrf2;
        this.rDiag = arrf3;
        this.eStates = arrf4;
        this.computeCellsFLL = cellCalculator;
        this.computeCellsBLL = cellCalculator2;
        this.computeCellsFL = cellCalculator3;
        this.computeCellsFR = cellCalculator4;
        this.computeCellsBL = cellCalculator5;
        this.computeCellsBR = cellCalculator6;
        this.passCellsF = cellCalculator7;
        this.passCellsB = cellCalculator8;
        this.passRunningTotal = procedure_Int;
        this.mI = matrixIterator;
        this.polygonsToMerge = new LinkedList<Object[]>();
        this.lineStack = floatStack;
        this.maxDistanceBetweenRescale = n5;
        this.reset = procedure_NoArgs;
    }

    final Object[] getPolygon(int n) {
        while (this.polygonsToMerge.size() != 0) {
            this.addPolygon(this.polygonsToMerge.remove(0));
        }
        Object[] arrobject = (Object[])this.polygonGen.fn(n);
        int[] arrn = (int[])arrobject[2];
        this.uptoDiagC = arrn[6] + arrn[7];
        return arrobject;
    }

    public final void addPolygon(int n) {
        int n2 = Integer.MIN_VALUE;
        do {
            if (this.currentDiag() + this.maxDistanceBetweenRescale < n - 1) {
                n2 = this.currentDiag() + this.maxDistanceBetweenRescale;
            } else {
                if (n2 >= n) break;
                n2 = n;
            }
            this.addPolygon(this.getPolygon(n2));
        } while (true);
    }

    private final void addPolygon(Object[] arrobject) {
        this.rStack.add(new Object[]{arrobject, new BackwardEvent(new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n, int n2, int n3) {
                ForwardBackwardMatrixIter.this.computeCellsBL.calc(arrf, n, n2, n3);
                ForwardBackwardMatrixIter.this.passCellsB.calc(arrf, n, n2, n3);
            }
        }, new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n, int n2, int n3) {
                ForwardBackwardMatrixIter.this.computeCellsBR.calc(arrf, n, n2, n3);
                ForwardBackwardMatrixIter.this.passCellsB.calc(arrf, n, n2, n3);
            }
        }, this.mI)});
        this.fStack.add(new Object[]{arrobject, new ForwardEvent(new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n, int n2, int n3) {
                ForwardBackwardMatrixIter.this.computeCellsFL.calc(arrf, n, n2, n3);
                ForwardBackwardMatrixIter.this.passCellsF.calc(arrf, n, n2, n3);
            }
        }, new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n, int n2, int n3) {
                ForwardBackwardMatrixIter.this.computeCellsFR.calc(arrf, n, n2, n3);
                ForwardBackwardMatrixIter.this.passCellsF.calc(arrf, n, n2, n3);
            }
        }, this.mI)});
    }

    public final void addCutPointGap(int n) {
        Object[] arrobject = this.getPolygon(n);
        this.rStack.add(new Object[]{arrobject, new BackwardEvent(this.computeCellsBL, this.computeCellsBR, this.mI)});
        this.polygonsToMerge.add(arrobject);
    }

    public final int currentDiag() {
        return this.uptoDiagC;
    }

    public final void cutPoint(int n, int n2) {
        System.arraycopy(this.holdingFDiagonal, 0, this.fDiag, 0, ((this.fDiagC + 1) * 2 + 1) * this.stateNumber);
        int[] arrn = (int[])((Object[])this.rStack.get(this.rStack.size() - 1)[0])[2];
        ForwardBackwardMatrixIter.getReversedEndDistribution(arrn, this.stateNumber, n - n2, this.eStates, this.rDiag);
        this.traceBackEvents(arrn[4] + 1, arrn[7] + 1, arrn[4] - arrn[6] + 1);
        System.arraycopy(this.fDiag, 0, this.holdingFDiagonal, 0, ((this.fDiagC + 1) * 2 + 1) * this.stateNumber);
    }

    final void traceBackEvents(int n, int n2, int n3) {
        Object[] arrobject;
        int n4;
        Object[] arrobject2;
        int[] arrn = null;
        if (this.polygonsToMerge.size() != 0) {
            ForwardBackwardMatrixIter.rescaleLine(this.rDiag, ((n3 + 1) * 2 + 1) * this.stateNumber);
            arrobject2 = this.rStack.remove(this.rStack.size() - 1);
            arrobject = (Object[])arrobject2[0];
            arrn = (int[])arrobject[2];
            ((Event)arrobject2[1]).event(n, n2, n3, this.rDiag, arrn, (List)arrobject[0], (List)arrobject[1], (int[])arrobject[3], (int[])arrobject[4]);
            n = arrn[2];
            n2 = arrn[1];
            n3 = n - arrn[0];
        }
        while (this.rStack.size() != 0) {
            ForwardBackwardMatrixIter.rescaleLine(this.rDiag, ((n3 + 1) * 2 + 1) * this.stateNumber);
            arrobject2 = this.rStack.remove(this.rStack.size() - 1);
            arrobject = (Object[])arrobject2[0];
            arrn = (int[])arrobject[2];
            ((Event)arrobject2[1]).event(n, n2, n3, this.rDiag, arrn, (List)arrobject[0], (List)arrobject[1], (int[])arrobject[3], (int[])arrobject[4]);
            n4 = ((arrn[2] - arrn[0] + 1) * 2 + 1) * this.stateNumber;
            for (int i = 0; i < n4; ++i) {
                this.lineStack.stuff(this.rDiag[i]);
            }
            n = arrn[2];
            n2 = arrn[1];
            n3 = n - arrn[0];
        }
        while (this.fStack.size() != 0) {
            arrobject2 = this.fStack.remove(0);
            arrobject = (Object[])arrobject2[0];
            arrn = (int[])arrobject[2];
            ForwardBackwardMatrixIter.rescaleLine(this.fDiag, ((this.fDiagC + 1) * 2 + 1) * this.stateNumber);
            n4 = ((arrn[2] - arrn[0] + 1) * 2 + 1) * this.stateNumber;
            for (int i = 0; i < n4; ++i) {
                this.rDiag[i] = this.lineStack.unstuff();
            }
            int[] arrn2 = (int[])arrobject[3];
            int[] arrn3 = (int[])arrobject[4];
            boolean bl = arrn2[0] != Integer.MAX_VALUE && arrn2[0] == arrn[0] && arrn2[1] == arrn[1];
            boolean bl2 = arrn2[0] != Integer.MAX_VALUE && arrn2[0] == arrn[0] && arrn2[1] == arrn[1] + 1;
            boolean bl3 = arrn3[0] != Integer.MAX_VALUE && arrn3[0] == arrn[2] && arrn3[1] == arrn[3];
            boolean bl4 = arrn3[0] != Integer.MAX_VALUE && arrn3[0] == arrn[2] + 1 && arrn3[1] == arrn[3];
            this.passRunningTotal.pro(Float.floatToRawIntBits(this.addTogetherDiagonals(this.x0, this.y0, this.fDiagC, this.fDiag, arrn, this.rDiag, bl, bl2, bl3, bl4)));
            ((Event)arrobject2[1]).event(this.x0, this.y0, this.fDiagC, this.fDiag, arrn, (List)arrobject[0], (List)arrobject[1], arrn2, arrn3);
            this.x0 = arrn[6];
            this.y0 = arrn[5];
            this.fDiagC = arrn[4] - this.x0;
        }
        this.reset.pro();
        if (!this.lineStack.empty()) {
            throw new IllegalStateException(this.lineStack.getMark() + " not empty ");
        }
    }

    final int[] getPolygonCoordinatesOfCurrentStack() {
        int[] arrn = new int[8];
        if (this.rStack.size() == 0) {
            throw new IllegalStateException();
        }
        int[] arrn2 = (int[])((Object[])this.rStack.get(0)[0])[2];
        int[] arrn3 = (int[])((Object[])this.rStack.get(this.rStack.size() - 1)[0])[2];
        System.arraycopy(arrn2, 0, arrn, 0, 4);
        System.arraycopy(arrn3, 4, arrn, 4, 4);
        return arrn;
    }

    static final void getReversedEndDistribution(int[] arrn, int n, int n2, float[] arrf, float[] arrf2) {
        int n3 = arrn[4] - arrn[6] + 2;
        int n4 = (1 + 2 * n3) * n;
        Arrays.fill(arrf2, 0, n4, Float.NEGATIVE_INFINITY);
        int n5 = n4 - 1 - (n2 - (arrn[6] - arrn[7] - 2)) * n;
        if (n5 > n4 - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < n; ++i) {
            arrf2[n5 - i] = arrf[i];
        }
    }

    final float addTogetherDiagonals(int n, int n2, int n3, float[] arrf, int[] arrn, float[] arrf2, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        int n4;
        float[] arrf3;
        int n5;
        int n6 = (arrn[2] - arrn[0] + 1) * 2 + 1;
        if (n + n2 + n3 + 2 == arrn[0] + arrn[1]) {
            n5 = 1;
            n4 = 1;
            arrf3 = new float[this.stateNumber];
            System.arraycopy(arrf, this.stateNumber, arrf3, 0, this.stateNumber);
        } else {
            n5 = n - n2 - n3 - 1 - (arrn[0] - arrn[1] - 1);
            n4 = n - n2 + n3 + 1 - (arrn[0] - arrn[1] - 1);
            arrf3 = arrf;
        }
        return ForwardBackwardMatrixIter.addTogetherDiagonals(arrf3, arrf2, n6, n5, n4, arrn[0], arrn[1] + 1, this.computeCellsFLL, this.stateNumber, bl, bl2, bl3, bl4);
    }

    static final float addTogetherDiagonals(float[] arrf, float[] arrf2, int n, int n2, int n3, int n4, int n5, Cell.CellCalculator cellCalculator, int n6, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        int n7;
        int n8;
        if (bl4) {
            --n;
        }
        float f = Float.NEGATIVE_INFINITY;
        float[] arrf3 = new float[4 * n6];
        Arrays.fill(arrf3, Float.NEGATIVE_INFINITY);
        int n9 = n2 > 0 ? n2 + n2 % 2 : (n7 = bl2 ? 2 : 0);
        while (n7 < n && n7 < n3) {
            System.arraycopy(arrf, (n7 - n2) * n6, arrf3, n6 * 2, n6);
            cellCalculator.calc(arrf3, 0, n4 + n7 / 2, n5 - n7 / 2);
            for (n8 = 0; n8 < n6; ++n8) {
                f = Maths.logAdd(f, arrf3[n8] + arrf2[n6 * n7 + n8]);
            }
            n7 += 2;
        }
        for (n7 = 1; n7 < n; n7 += 2) {
            if (n7 - 1 >= n2) {
                System.arraycopy(arrf, n6 * (n7 - 1 - n2), arrf3, n6, n6);
            }
            if (!bl || n7 > 1) {
                if (!bl3 || n7 + 2 < n) {
                    System.arraycopy(arrf, (n7 - n2) * n6, arrf3, n6 * 2, n6);
                } else {
                    Arrays.fill(arrf3, n6 * 2, n6 * 3, Float.NEGATIVE_INFINITY);
                }
            } else {
                Arrays.fill(arrf3, n6 * 2, n6 * 3, Float.NEGATIVE_INFINITY);
            }
            if (n7 + 1 < n3) {
                System.arraycopy(arrf, n6 * (n7 + 1 - n2), arrf3, n6 * 3, n6);
            } else {
                Arrays.fill(arrf3, n6 * 3, arrf3.length, Float.NEGATIVE_INFINITY);
            }
            cellCalculator.calc(arrf3, 0, n4 + n7 / 2, n5 - n7 / 2 - 1);
            for (n8 = 0; n8 < n6; ++n8) {
                f = Maths.logAdd(f, arrf3[n8] + arrf2[n6 * n7 + n8]);
            }
        }
        return f;
    }

    static final Cell.CellCalculator addToArgs(final int n, final int n2, final Cell.CellCalculator cellCalculator) {
        return new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n4, int n22, int n3) {
                cellCalculator.calc(arrf, n4, n22 + n, n3 + n2);
            }
        };
    }

    static final Cell.CellCalculator subtractArgsFrom(final int n, final int n2, final Cell.CellCalculator cellCalculator) {
        return new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n4, int n22, int n3) {
                cellCalculator.calc(arrf, n4, n - n22, n2 - n3);
            }
        };
    }

    public static final void rescaleLine(float[] arrf, int n) {
        int n2;
        float f = Float.NEGATIVE_INFINITY;
        for (n2 = 0; n2 < n; ++n2) {
            f = Maths.logAdd(f, arrf[n2]);
        }
        f -= (float)n;
        n2 = 0;
        while (n2 < n) {
            int n3 = n2++;
            arrf[n3] = arrf[n3] - f;
        }
    }

    public static Function_Index_3Args cutPointAlignmentGenerator(final ForwardBackwardMatrixIter forwardBackwardMatrixIter) {
        return new Function_Index_3Args(){

            public final Object fn(int n, int n2, int n3) {
                int n4;
                int n5 = forwardBackwardMatrixIter.currentDiag();
                if (n5 > (n4 = n + n2 - n3 - 1)) {
                    throw new IllegalStateException(n5 + " " + n4);
                }
                if (n5 < n4) {
                    forwardBackwardMatrixIter.addPolygon(n4);
                }
                int[] arrn = forwardBackwardMatrixIter.getPolygonCoordinatesOfCurrentStack();
                if (n3 != 0) {
                    forwardBackwardMatrixIter.addCutPointGap(n + n2 - 1);
                }
                forwardBackwardMatrixIter.cutPoint(n, n2);
                return arrn;
            }
        };
    }
    public static final class AlignmentGenerator {
        float total;
        final int matchState;
        final int reverseMatchState;
        final float greaterThanThreshold;
        final FloatStack stack;
        final Procedure_Int output;
        public final Cell.CellCalculator forwards = new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n, int n2, int n3) {
                float f = arrf[n + AlignmentGenerator.this.matchState] + AlignmentGenerator.this.stack.unstuff() - AlignmentGenerator.this.total;
                if (Float.isNaN(f)) {
                    throw new IllegalStateException(" Value is not a number " + n2 + " " + n3 + " " + arrf[n + AlignmentGenerator.this.matchState] + " " + AlignmentGenerator.this.total);
                }
                if (f > AlignmentGenerator.this.greaterThanThreshold) {
                    if ((double)f > 0.1) {
                        logger.info(" Value greater than one " + Math.exp(f) + " " + AlignmentGenerator.this.total + " " + n2 + " " + n3);
                    }
                    AlignmentGenerator.this.output.pro(Float.floatToRawIntBits(f > 0.0f ? 0.0f : f));
                    AlignmentGenerator.this.output.pro(n3);
                    AlignmentGenerator.this.output.pro(n2);
                }
            }
        };
        public final Cell.CellCalculator backwards = new Cell.CellCalculator(){

            public final void calc(float[] arrf, int n, int n2, int n3) {
                AlignmentGenerator.this.stack.stuff(arrf[n + AlignmentGenerator.this.reverseMatchState]);
            }
        };
        public final Procedure_Int passRunningTotal = new Procedure_Int(){

            public final void pro(int n) {
                AlignmentGenerator.this.total = Float.intBitsToFloat(n);
                if (Float.isNaN(AlignmentGenerator.this.total) || Float.isInfinite(AlignmentGenerator.this.total)) {
                    throw new IllegalStateException(" Total is unacceptable " + AlignmentGenerator.this.total);
                }
            }
        };
        public final Procedure_NoArgs reset = new Procedure_NoArgs(){

            public final void pro() {
                if (!AlignmentGenerator.this.stack.empty()) {
                    throw new IllegalStateException();
                }
            }
        };

        public AlignmentGenerator(int n, int n2, float f, FloatStack floatStack, Procedure_Int procedure_Int) {
            this.matchState = n2;
            this.reverseMatchState = n - 1 - n2;
            this.greaterThanThreshold = f;
            this.stack = floatStack;
            this.output = procedure_Int;
        }

    }
    public static final class BackwardEvent
    implements Event {
        Cell.CellCalculator backwardsL;
        Cell.CellCalculator backwardsR;
        MatrixIterator mI;

        public BackwardEvent(Cell.CellCalculator cellCalculator, Cell.CellCalculator cellCalculator2, MatrixIterator matrixIterator) {
            this.backwardsL = cellCalculator;
            this.backwardsR = cellCalculator2;
            this.mI = matrixIterator;
        }

        public final void event(int n, int n2, int n3, float[] arrf, int[] arrn, List list, List list2, int[] arrn2, int[] arrn3) {
            list = PolygonFiller.reverseCoordinates(list, n, n2);
            list2 = PolygonFiller.reverseCoordinates(list2, n, n2);
            int[] arrn4 = new int[arrn2.length];
            PolygonFiller.reverseLessThanCoordinates(arrn2, arrn4, n, n2);
            int[] arrn5 = new int[arrn3.length];
            PolygonFiller.reverseLessThanCoordinates(arrn3, arrn5, n, n2);
            this.mI.scanPolygon(list2, list, n - arrn[0] + 1, n2 - arrn[3] + 1, arrf, n3, ForwardBackwardMatrixIter.subtractArgsFrom(n, n2, this.backwardsL), ForwardBackwardMatrixIter.subtractArgsFrom(n, n2, this.backwardsR), arrn5, arrn4);
            this.mI.getFinalValues(arrf, n + n2 - arrn[0] - arrn[1]);
        }
    }
    public static final class ForwardEvent
    implements Event {
        Cell.CellCalculator forwardsL;
        Cell.CellCalculator forwardsR;
        MatrixIterator mI;

        public ForwardEvent(Cell.CellCalculator cellCalculator, Cell.CellCalculator cellCalculator2, MatrixIterator matrixIterator) {
            this.forwardsL = cellCalculator;
            this.forwardsR = cellCalculator2;
            this.mI = matrixIterator;
        }

        public final void event(int n, int n2, int n3, float[] arrf, int[] arrn, List list, List list2, int[] arrn2, int[] arrn3) {
            PolygonFiller.transformEdgeList(list, -n, -n2);
            PolygonFiller.transformEdgeList(list2, -n, -n2);
            int[] arrn4 = new int[arrn2.length];
            int[] arrn5 = new int[arrn3.length];
            PolygonFiller.transformCoordinates(arrn2, arrn4, -n, -n2);
            PolygonFiller.transformCoordinates(arrn3, arrn5, -n, -n2);
            this.mI.scanPolygon(list, list2, arrn[4] - n + 1, arrn[7] - n2 + 1, arrf, n3, ForwardBackwardMatrixIter.addToArgs(n, n2, this.forwardsL), ForwardBackwardMatrixIter.addToArgs(n, n2, this.forwardsR), arrn4, arrn5);
            this.mI.getFinalValues(arrf, arrn[4] + arrn[5] - n - n2);
        }
    }
    static interface Event {
        public void event(int var1, int var2, int var3, float[] var4, int[] var5, List var6, List var7, int[] var8, int[] var9);
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.Array;
import bp.common.ds.IntCrapHash;
import bp.common.ds.IntStack;
import bp.common.ds.ScrollingQueue_Int;
import bp.common.ds.wrappers.ObjectNode;
import bp.common.fp.Function;
import bp.common.fp.Function_Int_2Args;
import bp.common.fp.Function_Int_3Args;
import bp.common.fp.Generator;
import bp.common.fp.Generator_Int;
import bp.common.fp.IterationTools;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_Int;
import bp.common.fp.Procedure_Int_2Args;
import bp.common.fp.Procedure_Int_5Args;
import bp.common.fp.Procedure_NoArgs;
import bp.common.maths.Maths;
import bp.pecan.Chains;
import bp.pecan.PairsHeap;
import bp.pecan.Pecan;
import java.util.Arrays;
import java.util.logging.Logger;

public final class Librarian {
    static final Logger logger = Logger.getLogger(Pecan.class.getName());

    public static final int sum(int n, int n2) {
        return Float.floatToRawIntBits(Float.intBitsToFloat(n) + Float.intBitsToFloat(n2));
    }

    public static final boolean greaterThanOrEqual(int n, int n2) {
        return Float.intBitsToFloat(n) >= Float.intBitsToFloat(n2);
    }

    public static final int multiply(int n, int n2) {
        return Float.floatToRawIntBits(Float.intBitsToFloat(n) * Float.intBitsToFloat(n2));
    }

    public static final int convertWeight(int n) {
        return Float.floatToRawIntBits(Maths.exp(Float.intBitsToFloat(n)));
    }

    static final int sumWeights(int[] arrn, int n, int n2) {
        if (n < n2) {
            int n3 = arrn[n + 1];
            for (int i = n + 3; i < n2; i += 2) {
                n3 = Librarian.sum(arrn[i], n3);
            }
            return n3;
        }
        return Integer.MIN_VALUE;
    }

    public static final WeightsGetter weightsGetter_FilterByGapThreshold(final PairsHeap[][] arrpairsHeap, final float f) {
        return new WeightsGetter(){
            int iGamma;
            {
                this.iGamma = Float.floatToRawIntBits(f);
            }

            int convertToThreshold(int n) {
                return Librarian.multiply(Float.floatToRawIntBits(1.0f - Float.intBitsToFloat(n)), this.iGamma);
            }

            public final int fn(int n, int n2, int n3, int[] arrn, int n4) {
                int n5 = arrpairsHeap[n2][n3].get(n, n4, arrn);
                int n6 = n4;
                int n7 = this.convertToThreshold(Librarian.sumWeights(arrn, n4, n5));
                PairsHeap pairsHeap = arrpairsHeap[n3][n2];
                for (int i = n4; i < n5; i += 2) {
                    int n8;
                    int n9;
                    if (!Librarian.greaterThanOrEqual(arrn[i + 1], n7) || !Librarian.greaterThanOrEqual(arrn[i + 1], n9 = this.convertToThreshold(Librarian.sumWeights(arrn, n5, n8 = pairsHeap.get(arrn[i], n5, arrn))))) continue;
                    arrn[n6++] = arrn[i];
                    arrn[n6++] = arrn[i + 1];
                }
                return n6;
            }
        };
    }

    public static final WeightsGetter weightsGetter(final PairsHeap[][] arrpairsHeap) {
        return new WeightsGetter(){

            public final int fn(int n, int n2, int n3, int[] arrn, int n4) {
                return arrpairsHeap[n2][n3].get(n, n4, arrn);
            }
        };
    }

    public static final WeightsGetter weightsGetter_Consistency(final PairsHeap[][] arrpairsHeap) {
        return new WeightsGetter(){
            int[] iA2 = new int[300];
            IntCrapHash iH = new IntCrapHash(500);
            Function_Int_2Args fn = new Function_Int_2Args(){

                public int fn(int n, int n2) {
                    return Librarian.sum(n, n2);
                }
            };
            final int multiple = Float.floatToIntBits(arrpairsHeap.length > 2 ? (float)(arrpairsHeap.length - 2) : 1.0f);

            public final int fn(int n, int n2, int n3, int[] arrn, int n4) {
                int n5;
                PairsHeap[] arrpairsHeap2 = arrpairsHeap[n2];
                int n6 = arrpairsHeap2[n3].get(n, 0, arrn);
                for (n5 = 0; n5 < n6; n5 += 2) {
                    this.iH.put(arrn[n5], Librarian.multiply(arrn[n5 + 1], this.multiple), null);
                }
                for (n5 = 0; n5 < arrpairsHeap2.length; ++n5) {
                    if (n5 == n2 || n5 == n3) continue;
                    n6 = arrpairsHeap2[n5].get(n, 0, arrn);
                    for (int i = 0; i < n6; i += 2) {
                        int n7 = arrn[i];
                        int n8 = arrn[i + 1];
                        int n9 = arrpairsHeap[n5][n3].get(n7, 0, this.iA2);
                        for (int j = 0; j < n9; j += 2) {
                            int n10 = Librarian.multiply(n8, this.iA2[j + 1]);
                            this.iH.put(this.iA2[j], n10, this.fn);
                        }
                    }
                }
                n6 = this.iH.getEntries(arrn);
                this.iH.clear();
                return n6;
            }

        };
    }

    public static final Function_Int_3Args weightsTotal(final PairsHeap[][] arrpairsHeap, final int n) {
        return new Function_Int_3Args(){

            public final int fn(int n4, int n2, int n3) {
                return arrpairsHeap[n2][n3].sumTotal(n4, n);
            }
        };
    }

    public static final Function_Int_3Args getMaxResidue(final ScrollingQueue_Int[][] arrscrollingQueue_Int) {
        return new Function_Int_3Args(){

            public final int fn(int n, int n2, int n3) {
                return arrscrollingQueue_Int[n2][n3].get(n);
            }
        };
    }

    public static void coordinateAlignment(Function[][] arrfunction, Generator generator, final Procedure_Int[] arrprocedure_Int, PairsHeap[][] arrpairsHeap, int n, IntStack intStack, int n2, Procedure_Int_2Args[][] arrprocedure_Int_2Args) {
        int[][][] arrn = Librarian.iAAAMake(n);
        TransitiveDependencies transitiveDependencies = new TransitiveDependencies(n, arrn, Librarian.completedOffsets(n, arrn, new Procedure_Int_2Args(){

            public final void pro(int n, int n2) {
                arrprocedure_Int[n].pro(n2);
            }
        }));
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                transitiveDependencies.updateTransitiveDependencies(i, j, arrpairsHeap[i][j].firstIndex() - 1);
                transitiveDependencies.updateTransitiveDependencies(j, i, arrpairsHeap[j][i].firstIndex() - 1);
            }
        }
        Procedure[][] arrprocedure = Librarian.coordinateAlignment(arrpairsHeap, n, transitiveDependencies, n2, intStack);
        Librarian.coordinatePairs(generator, arrfunction, arrprocedure, transitiveDependencies, intStack, arrprocedure_Int_2Args);
        transitiveDependencies.checkEmpty();
    }

    static void coordinatePairs(Generator generator, Function[][] arrfunction, Procedure[][] arrprocedure, TransitiveDependencies transitiveDependencies, IntStack intStack, Procedure_Int_2Args[][] arrprocedure_Int_2Args) {
        Chains.CutPoint cutPoint;
        int[][][] arrn = transitiveDependencies.getSequenceOffsets();
        while ((cutPoint = (Chains.CutPoint)generator.gen()) != null) {
            boolean bl;
            boolean bl2 = bl = cutPoint.tB == Integer.MAX_VALUE;
            if (bl) {
                cutPoint.tB = 0;
            }
            int[] arrn2 = (int[])arrfunction[cutPoint.s1][cutPoint.s2].fn(cutPoint);
            int n = bl || arrn[cutPoint.s1][cutPoint.s2][cutPoint.s2] == arrn2[6] ? arrn2[6] : arrn2[6] - 1;
            int n2 = bl || arrn[cutPoint.s2][cutPoint.s1][cutPoint.s1] == arrn2[5] ? arrn2[5] : arrn2[5] - 1;
            arrprocedure[cutPoint.s1][cutPoint.s2].pro(new int[]{n, n2});
            if (!intStack.empty()) {
                throw new IllegalArgumentException();
            }
            arrprocedure_Int_2Args[cutPoint.s1][cutPoint.s2].pro(arrn2[4], n);
            arrprocedure_Int_2Args[cutPoint.s2][cutPoint.s1].pro(arrn2[7], n2);
            transitiveDependencies.updateTransitiveDependencies(cutPoint.s1, cutPoint.s2, n);
            transitiveDependencies.updateTransitiveDependencies(cutPoint.s2, cutPoint.s1, n2);
        }
    }

    public static Procedure[][] coordinateAlignment(PairsHeap[][] arrpairsHeap, int n, TransitiveDependencies transitiveDependencies, int n2, IntStack intStack) {
        Procedure[][] arrprocedure = new Procedure[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                Procedure_NoArgs procedure_NoArgs = Librarian.processAlignment_NoConsistency(arrpairsHeap[i], i, j, intStack);
                arrprocedure[i][j] = Librarian.coordinateAlignment(i, j, arrpairsHeap[i], arrpairsHeap[j], transitiveDependencies, n2, procedure_NoArgs);
            }
        }
        return arrprocedure;
    }

    static Procedure coordinateAlignment(final int n, final int n2, final PairsHeap[] arrpairsHeap, final PairsHeap[] arrpairsHeap2, final TransitiveDependencies transitiveDependencies, final int n3, final Procedure_NoArgs procedure_NoArgs) {
        return new Procedure(){
            int kI;
            int lI;
            int kJ;
            int lJ;
            {
                this.kI = arrpairsHeap[n2].firstIndex() - 1;
                this.kJ = this.lI = arrpairsHeap2[n].firstIndex() - 1;
                this.lJ = this.kI;
            }

            public void pro(Object object) {
                int[] arrn = (int[])object;
                int n4 = arrn[0];
                int n22 = arrn[1];
                procedure_NoArgs.pro();
                int[] arrn2 = Librarian.createRelationships(transitiveDependencies, arrpairsHeap[n2], this.kI, n4, n, n2, this.lI, this.kI + this.lI - 1, n3);
                this.kI = n4 + 1;
                this.lI = arrn2[0];
                transitiveDependencies.insertRelationship(n2, n, this.lI, n4);
                arrn2 = Librarian.createRelationships(transitiveDependencies, arrpairsHeap2[n], this.kJ, n22, n2, n, this.lJ, this.kJ + this.lJ - 1, n3);
                this.kJ = n22 + 1;
                this.lJ = arrn2[0];
                transitiveDependencies.insertRelationship(n, n2, this.lJ, n22);
            }
        };
    }

    static Procedure_Int_2Args updateMaxOffsets(final int n, final int n2, final ScrollingQueue_Int[][] arrscrollingQueue_Int, final Procedure_Int[][] arrprocedure_Int, final WeightsGetter weightsGetter, final int[] arrn, final int n3) {
        return new Procedure_Int_2Args(){
            final ScrollingQueue_Int sQIJ;
            final ScrollingQueue_Int[] sQAJ;
            final Procedure_Int genIJ;
            final Procedure_Int[] gensJ;
            int k;
            {
                this.sQIJ = arrscrollingQueue_Int[n][n2];
                this.sQAJ = arrscrollingQueue_Int[n2];
                this.genIJ = arrprocedure_Int[n][n2];
                this.gensJ = arrprocedure_Int[n2];
                this.k = this.sQIJ.firstIndex();
            }

            public void pro(int n11, int n22) {
                this.genIJ.pro(n11 + 1);
                while (this.k <= n11) {
                    int n32 = this.k;
                    int n4 = this.getWeights(n32, n, n2, arrn, 0);
                    if (n4 != 0) {
                        int n5 = arrn[0];
                        int n6 = this.sQIJ.get(this.k);
                        if (n6 == Integer.MAX_VALUE || n5 > n6) {
                            this.sQIJ.set(this.k, n5);
                        }
                        for (int i = 0; i < n3; ++i) {
                            int n7;
                            int n8;
                            int n9;
                            if (i == n || i == n2 || (n8 = this.getWeights(n32, n, i, arrn, n4)) == n4) continue;
                            int n10 = arrn[n4];
                            ScrollingQueue_Int scrollingQueue_Int = this.sQAJ[i];
                            this.gensJ[i].pro(n5 + 1);
                            for (n7 = 0; n7 < n4; n7 += 2) {
                                n9 = arrn[n7];
                                n6 = scrollingQueue_Int.get(n9);
                                if (n6 != Integer.MAX_VALUE && n10 <= n6) continue;
                                scrollingQueue_Int.set(n9, n10);
                            }
                            arrprocedure_Int[i][n2].pro(n10 + 1);
                            scrollingQueue_Int = arrscrollingQueue_Int[i][n2];
                            for (n7 = n4; n7 < n8; n7 += 2) {
                                n9 = arrn[n7];
                                n6 = scrollingQueue_Int.get(n9);
                                if (n6 != Integer.MAX_VALUE && n5 <= n6) continue;
                                scrollingQueue_Int.set(n9, n5);
                            }
                        }
                    }
                    ++this.k;
                }
                this.k = n22 + 1;
            }

            int getWeights(int n6, int n22, int n32, int[] arrn2, int n4) {
                int n5 = weightsGetter.fn(n6, n22, n32, arrn2, n4);
                return n5;
            }
        };
    }

    static int[] createRelationships(TransitiveDependencies transitiveDependencies, PairsHeap pairsHeap, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (pairsHeap.firstIndex() > n) {
            n = pairsHeap.firstIndex();
        }
        while (n <= n2) {
            int n8 = pairsHeap.getRightmostPoint(n);
            if (n8 != Integer.MAX_VALUE && n8 > n5) {
                n5 = n8;
            }
            if (n + n5 >= n6 + n7) {
                transitiveDependencies.insertRelationship(n4, n3, n5, n);
                n6 = n + n5;
            }
            ++n;
        }
        return new int[]{n5, n6};
    }

    static Procedure_NoArgs processAlignment_NoConsistency(final PairsHeap[] arrpairsHeap, int n, final int n2, final Generator_Int generator_Int) {
        return new Procedure_NoArgs(){

            public void pro() {
                int n;
                Generator_Int generator_Int2 = generator_Int;
                while ((n = generator_Int2.gen()) != Integer.MAX_VALUE) {
                    int n22 = generator_Int2.gen();
                    int n3 = Librarian.convertWeight(generator_Int2.gen());
                    arrpairsHeap[n2].add(n, n22, n3);
                }
            }
        };
    }

    static int[][] iAAMake(int n) {
        int[][] arrn = new int[n][n];
        for (int i = 0; i < arrn.length; ++i) {
            int[] arrn2 = arrn[i];
            Arrays.fill(arrn2, Integer.MIN_VALUE);
            arrn2[i] = Integer.MAX_VALUE;
        }
        return arrn;
    }

    static int[] iAMake(int n) {
        int[] arrn = new int[n];
        Arrays.fill(arrn, Integer.MIN_VALUE);
        return arrn;
    }

    static Procedure_Int_5Args completedOffsets(final int n, final int[][][] arrn, final Procedure_Int_2Args procedure_Int_2Args) {
        return new Procedure_Int_5Args(){
            int[][] iAA;
            int[] iA;
            {
                this.iAA = Librarian.iAAMake(n);
                this.iA = Librarian.iAMake(n);
            }

            public void pro(int n7, int n2, int n3, int n4, int n5) {
                if (this.iAA[n7][n2] == n4) {
                    int n6 = Array.getMin(arrn[n7][n2]);
                    if (this.iAA[n7][n2] == this.iA[n7]) {
                        this.iAA[n7][n2] = n6;
                        n2 = this.iA[n7];
                        this.iA[n7] = Array.getMin(this.iAA[n7]);
                        if (this.iA[n7] > n2) {
                            procedure_Int_2Args.pro(n7, this.iA[n7]);
                        }
                    } else {
                        this.iAA[n7][n2] = n6;
                    }
                }
            }
        };
    }

    static int[][][] iAAAMake(int n) {
        int[][][] arrn = new int[n][n][];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int[] arrn2 = new int[n];
                arrn[i][j] = arrn2;
                int[] arrn3 = arrn2;
                int[] arrn4 = new int[n];
                arrn[j][i] = arrn4;
                int[] arrn5 = arrn4;
                Arrays.fill(arrn3, Integer.MIN_VALUE);
                Arrays.fill(arrn5, Integer.MIN_VALUE);
                arrn3[i] = Integer.MAX_VALUE;
                arrn5[j] = Integer.MAX_VALUE;
            }
        }
        return arrn;
    }
    static class TransitiveDependencies {
        private static final int COORDINATE = 0;
        private static final int OTHERSEQCOORDINATE = 1;
        private final int seqNo;
        private final int[][][] iAAA;
        private final ObjectNode[][] oNAA;
        private final ObjectNode[][][] oNAAA;
        final Procedure_Int_5Args pro;

        public TransitiveDependencies(int n, int[][][] arrn, Procedure_Int_5Args procedure_Int_5Args) {
            int n2;
            this.seqNo = n;
            this.iAAA = arrn;
            this.pro = procedure_Int_5Args;
            this.oNAA = new ObjectNode[n][n];
            for (n2 = 0; n2 < this.oNAA.length; ++n2) {
                ObjectNode[] arrobjectNode = this.oNAA[n2];
                for (int i = 0; i < arrobjectNode.length; ++i) {
                    if (n2 == i) continue;
                    arrobjectNode[i] = new ObjectNode();
                }
            }
            this.oNAAA = new ObjectNode[n][n][n];
            for (n2 = 0; n2 < n; ++n2) {
                for (int i = 0; i < n; ++i) {
                    if (n2 == i) continue;
                    ObjectNode[] arrobjectNode = this.oNAAA[n2][i];
                    for (int j = 0; j < n; ++j) {
                        if (j == i) continue;
                        arrobjectNode[j] = this.oNAA[n2][j];
                    }
                }
            }
        }

        public final void checkEmpty() {
            int n;
            int n2;
            for (int i = 0; i < this.oNAAA.length; ++i) {
                for (n = 0; n < this.oNAAA[i].length; ++n) {
                    for (n2 = 0; n2 < this.oNAAA[i][n].length; ++n2) {
                        if (this.oNAAA[i][n][n2] == null || this.oNAAA[i][n][n2].pointer == null) continue;
                        throw new IllegalStateException(i + " " + n + " " + n2 + " " + this.oNAAA[i][n][n2] + " " + IterationTools.join((int[])this.oNAAA[i][n][n2].pointer.o, " "));
                    }
                }
            }
            ObjectNode[][] arrobjectNode = this.oNAA;
            n = arrobjectNode.length;
            for (n2 = 0; n2 < n; ++n2) {
                ObjectNode[] arrobjectNode2;
                for (ObjectNode objectNode : arrobjectNode2 = arrobjectNode[n2]) {
                    if (objectNode == null || objectNode.pointer == null) continue;
                    throw new IllegalStateException();
                }
            }
        }

        public final void updateTransitiveDependencies(int n, int n2, int n3) {
            int n4 = this.iAAA[n][n2][n2];
            this.iAAA[n][n2][n2] = n3;
            this.pro.pro(n, n2, n2, n4, n3);
            TransitiveDependencies.updateTransitiveDependencies1(this.seqNo, this.iAAA, this.pro, this.oNAAA, n, n2, n3);
            TransitiveDependencies.updateTransitiveDependencies2(this.seqNo, this.pro, this.iAAA, this.oNAAA, n, n2);
        }

        public void insertRelationship(int n, int n2, int n3, int n4) {
            ObjectNode objectNode = new ObjectNode(new int[]{n3, n4}, null);
            ObjectNode[] arrobjectNode = this.oNAA[n];
            arrobjectNode[n2].pointer = objectNode;
            arrobjectNode[n2] = objectNode;
        }

        private static final void updateTransitiveDependencies2(int n, Procedure_Int_5Args procedure_Int_5Args, int[][][] arrn, ObjectNode[][][] arrobjectNode, int n2, int n3) {
            int[][] arrn2 = arrn[n2];
            int[][] arrn3 = arrn[n3];
            ObjectNode[][] arrobjectNode2 = arrobjectNode[n2];
            for (int i = 0; i < n; ++i) {
                int[] arrn4;
                if (i == n2 || i == n3) continue;
                ObjectNode[] arrobjectNode3 = arrobjectNode2[i];
                ObjectNode objectNode = arrobjectNode3[n3];
                int n4 = arrn2[i][i];
                while (objectNode.pointer != null && n4 >= (arrn4 = (int[])objectNode.pointer.o)[0]) {
                    int n5 = arrn3[i][n2];
                    arrn3[i][n2] = arrn4[1];
                    procedure_Int_5Args.pro(n3, i, n2, n5, arrn4[1]);
                    arrobjectNode3[n3] = objectNode = objectNode.pointer;
                }
            }
        }

        private static final void updateTransitiveDependencies1(int n, int[][][] arrn, Procedure_Int_5Args procedure_Int_5Args, ObjectNode[][][] arrobjectNode, int n2, int n3, int n4) {
            ObjectNode[] arrobjectNode2 = arrobjectNode[n2][n3];
            for (int i = 0; i < n; ++i) {
                int[] arrn2;
                if (i == n2 || i == n3) continue;
                ObjectNode objectNode = arrobjectNode2[i];
                while (objectNode.pointer != null && (arrn2 = (int[])objectNode.pointer.o)[0] <= n4) {
                    int n5 = arrn[i][n3][n2];
                    arrn[i][n3][n2] = arrn2[1];
                    procedure_Int_5Args.pro(i, n3, n2, n5, arrn2[1]);
                    arrobjectNode2[i] = objectNode = objectNode.pointer;
                }
            }
        }

        public int[][][] getSequenceOffsets() {
            return this.iAAA;
        }
    }
    static final class FreeMemory
    implements Procedure_Int_2Args,
    Procedure_Int {
        int[][] iAA;
        int[] iA;
        PairsHeap[][] sQA;
        int seqNo;

        public FreeMemory(PairsHeap[][] arrpairsHeap, int[][] arrn, int n) {
            this.iAA = new int[n][n];
            this.iA = new int[n];
            for (int i = 0; i < n; ++i) {
                int n2;
                this.iA[i] = n2 = arrn[i][0] - 1;
                Arrays.fill(this.iAA[i], n2);
            }
            this.sQA = arrpairsHeap;
            this.seqNo = n;
        }

        public final void pro(int n, int n2) {
            if (this.iAA[n][n] > n2) {
                throw new IllegalStateException(this.iAA[n][n] + " " + n2);
            }
            this.iAA[n][n] = n2;
        }

        public final void pro(int n) {
            int n2;
            int n3;
            int[] arrn = this.iAA[n];
            int n4 = arrn[n];
            PairsHeap[] arrpairsHeap = this.sQA[n];
            for (n3 = 0; n3 < arrn.length; ++n3) {
                int n5;
                if (n3 == n) continue;
                PairsHeap pairsHeap = arrpairsHeap[n3];
                int n6 = this.iAA[n3][n3];
                for (n2 = arrn[n3] + 1; n2 <= n4 && ((n5 = pairsHeap.getRightmostPoint(n2)) == Integer.MAX_VALUE || n5 <= n6); ++n2) {
                }
                arrn[n3] = n2 - 1;
            }
            n3 = Array.getMin(arrn);
            if (n3 > this.iA[n]) {
                this.iA[n] = n3;
                for (n2 = 0; n2 < n; ++n2) {
                    arrpairsHeap[n2].tryToRemoveUpto(n3 + 1);
                }
                for (n2 = n + 1; n2 < this.seqNo; ++n2) {
                    arrpairsHeap[n2].tryToRemoveUpto(n3 + 1);
                }
            }
        }
    }
    public static interface WeightsGetter {
        public int fn(int var1, int var2, int var3, int[] var4, int var5);
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.fp.Procedure_Int_2Args;
import bp.pecan.Cell;
import bp.pecan.PolygonFiller;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class MatrixIterator
implements Procedure_Int_2Args {
    private final int stateNumber;
    private Cell.CellCalculator calcL;
    private Cell.CellCalculator calcR;
    private Cell.CellCalculator calc;
    final float[] line;
    final float[] scratch;
    private boolean lineFlip;
    private int y;
    int width;
    int height;
    int offset;
    private int initialDiagonal;
    int[] lLTA;
    int[] rLTA;
    int lLTAi;
    int rLTAi;
    int lLT;
    int rLT;

    public MatrixIterator(int n, int n2) {
        n2 = (n2 + 1) * n * 2;
        this.line = new float[n2];
        this.stateNumber = n;
        this.scratch = new float[n];
    }

    public final void pro(int n, int n2) {
        int n3;
        this.offset = (n + 1) * this.stateNumber;
        boolean bl = this.lineFlip = !this.lineFlip;
        if (this.lineFlip) {
            this.calc = this.calcR;
            this.offset += this.line.length / 2;
        } else {
            this.calc = this.calcL;
        }
        if (this.y > this.initialDiagonal) {
            Arrays.fill(this.line, this.offset - this.stateNumber, this.offset, Float.NEGATIVE_INFINITY);
        }
        if (this.lLT == this.y) {
            if (this.lLTA[this.lLTAi] == n) {
                n3 = this.offset + (this.lineFlip ? -this.line.length / 2 : this.line.length / 2) - this.stateNumber;
                System.arraycopy(this.line, n3, this.scratch, 0, this.stateNumber);
                Arrays.fill(this.line, n3, n3 + this.stateNumber, Float.NEGATIVE_INFINITY);
                this.calc.calc(this.line, this.offset, n++, this.y);
                System.arraycopy(this.scratch, 0, this.line, n3, this.stateNumber);
                this.offset += this.stateNumber;
            }
            this.lLTAi += 2;
            this.lLT = this.lLTA[this.lLTAi + 1];
        }
        if (this.rLT == this.y) {
            n3 = this.rLTA[this.rLTAi];
            if (n3 >= n && n3 <= n2) {
                while (n < n3) {
                    this.calc.calc(this.line, this.offset, n++, this.y);
                    this.offset += this.stateNumber;
                }
                int n4 = this.offset + (this.lineFlip ? -this.line.length / 2 : this.line.length / 2) - this.stateNumber;
                System.arraycopy(this.line, n4, this.scratch, 0, this.stateNumber);
                Arrays.fill(this.line, n4, n4 + this.stateNumber, Float.NEGATIVE_INFINITY);
                this.calc.calc(this.line, this.offset, n++, this.y);
                System.arraycopy(this.scratch, 0, this.line, n4, this.stateNumber);
                this.offset += this.stateNumber;
            }
            this.rLTAi += 2;
            this.rLT = this.rLTA[this.rLTAi + 1];
        }
        while (n <= n2) {
            this.calc.calc(this.line, this.offset, n++, this.y);
            this.offset += this.stateNumber;
        }
        ++this.y;
    }

    public final void getFinalValues(float[] arrf, int n) {
        int n2;
        if (n - Math.max(this.height, this.width) < -1 || n < 0) {
            throw new IllegalStateException();
        }
        int n3 = 0;
        boolean bl = this.lineFlip;
        for (n2 = n - this.height + 1; n2 < this.width; ++n2) {
            boolean bl2 = bl = !bl;
            if (bl) {
                System.arraycopy(this.line, n2 * this.stateNumber, arrf, n3++ * 2 * this.stateNumber, this.stateNumber * 2);
                continue;
            }
            System.arraycopy(this.line, n2 * this.stateNumber + this.line.length / 2, arrf, n3++ * 2 * this.stateNumber, this.stateNumber * 2);
        }
        if (!bl) {
            System.arraycopy(this.line, n2 * this.stateNumber, arrf, n3 * 2 * this.stateNumber, this.stateNumber);
        } else {
            System.arraycopy(this.line, n2 * this.stateNumber + this.line.length / 2, arrf, n3 * 2 * this.stateNumber, this.stateNumber);
        }
    }

    final void setInitialValues(float[] arrf, int n) {
        int n2;
        if (Math.min(this.height, this.width) - n <= 0 || n < 0) {
            throw new IllegalStateException();
        }
        boolean bl = n % 2 != 0;
        int n3 = 0;
        for (n2 = 0; n2 <= n; ++n2) {
            boolean bl2 = bl = !bl;
            if (bl) {
                System.arraycopy(arrf, n3++ * 2 * this.stateNumber, this.line, n2 * this.stateNumber, this.stateNumber * 2);
                continue;
            }
            System.arraycopy(arrf, n3++ * 2 * this.stateNumber, this.line, n2 * this.stateNumber + this.line.length / 2, this.stateNumber * 2);
        }
        if (!bl) {
            System.arraycopy(arrf, n3 * 2 * this.stateNumber, this.line, n2 * this.stateNumber, this.stateNumber);
        } else {
            System.arraycopy(arrf, n3 * 2 * this.stateNumber, this.line, n2 * this.stateNumber + this.line.length / 2, this.stateNumber);
        }
    }

    public final void scanPolygon(List list, List list2, int n, int n2, float[] arrf, int n3, Cell.CellCalculator cellCalculator, Cell.CellCalculator cellCalculator2, int[] arrn, int[] arrn2) {
        this.calcL = cellCalculator;
        this.calcR = cellCalculator2;
        this.width = n;
        this.height = n2;
        int n4 = (n + 1) * this.stateNumber;
        Arrays.fill(this.line, 0, n4, Float.NEGATIVE_INFINITY);
        Arrays.fill(this.line, this.line.length / 2, this.line.length / 2 + n4, Float.NEGATIVE_INFINITY);
        this.setInitialValues(arrf, n3);
        this.initialDiagonal = n3;
        this.y = ((PolygonFiller.Node)list.get((int)0)).y;
        boolean bl = this.lineFlip = this.y == 0;
        if (!this.lineFlip && ((PolygonFiller.Node)list.get((int)0)).y != 1) {
            throw new IllegalStateException(((PolygonFiller.Node)list.get((int)0)).y + " ");
        }
        this.lLTA = arrn;
        this.rLTA = arrn2;
        this.lLTAi = 0;
        this.lLT = arrn[1];
        this.rLTAi = 0;
        this.rLT = arrn2[1];
        PolygonFiller.scanPolygon(list.iterator(), list2.iterator(), this);
        if (this.y != n2) {
            throw new IllegalStateException(this.y + " " + n2);
        }
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.ScrollingQueue_Int;
import bp.pecan.Librarian;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.logging.Logger;

final class PairsHeap {
    static final Logger logger = Logger.getLogger(PairsHeap.class.getName());
    private static IntBuffer iB;
    private static float expansionRate;
    private static int stackPointer;
    private static boolean useDirectBuffers;
    private static int used;
    private static int maxSize;
    public static long totalUsed12;
    private final ScrollingQueue_Int sQI;
    private PairsHeap partner;
    final boolean weightPointers;
    private int finishedUpto;

    private PairsHeap(int n, int n2, boolean bl) {
        this.sQI = new ScrollingQueue_Int(n2, n, true);
        this.weightPointers = bl;
    }

    public static void initialise(int n, float f, boolean bl) {
        if (n < 12) {
            throw new IllegalStateException();
        }
        n -= n % 12;
        if ((double)f <= 1.0) {
            throw new IllegalStateException();
        }
        logger.info(" Initialising pairs heap to capacity (bytes) : " + n);
        expansionRate = f;
        useDirectBuffers = bl;
        iB = PairsHeap.getBuffer(n);
        iB.put(0, Integer.MAX_VALUE);
        stackPointer = 0;
        used = 0;
    }

    private static final IntBuffer getBuffer(int n) {
        try {
            return useDirectBuffers ? ByteBuffer.allocateDirect(n).asIntBuffer() : ByteBuffer.allocate(n).asIntBuffer();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            logger.info("Caught out of memory exception for pairs heap, calling the gc and trying to allocate non-directly");
            System.gc();
            return ByteBuffer.allocate(n).asIntBuffer();
        }
    }

    public static PairsHeap[] getPair(int n, int n2, int n3) {
        PairsHeap pairsHeap;
        PairsHeap pairsHeap2 = new PairsHeap(n, n3, false);
        pairsHeap2.partner = pairsHeap = new PairsHeap(n2, n3, true);
        pairsHeap.partner = pairsHeap2;
        return new PairsHeap[]{pairsHeap2, pairsHeap};
    }

    private static final void enlarge() {
        logger.info(" Calling enlarge with iB capacity (bytes) " + 4 * iB.capacity() + " " + expansionRate);
        int n = iB.capacity();
        int n2 = 3 + (int)((float)n * expansionRate);
        n2 += 3 - n2 % 3;
        IntBuffer intBuffer = PairsHeap.getBuffer(n2 * 4);
        iB.clear();
        intBuffer.put(iB);
        intBuffer.put(n, Integer.MAX_VALUE);
        Librarian.logger.info("Expanded to iB " + 4 * iB.capacity());
        iB = intBuffer;
    }

    public final int firstIndex() {
        return this.sQI.firstIndex();
    }

    public final int lastActualIndex() {
        return this.sQI.lastIndex();
    }

    public final void tryToRemoveUpto(int n) {
        if (this.weightPointers) {
            while (this.sQI.firstIndex() < n) {
                this.releaseFirst();
            }
            this.partner.tryToRemoveUpto(this.partner.finishedUpto);
            return;
        }
        this.finishedUpto = n;
        int n2 = this.sQI.firstIndex();
        while (n2 < n) {
            int n3 = this.getRightmostPoint(n2);
            if (n3 != Integer.MAX_VALUE && n3 >= this.partner.firstIndex()) {
                return;
            }
            this.releaseFirst();
            n2 = this.sQI.firstIndex();
        }
    }

    private final void releaseFirst() {
        int n = this.sQI.firstIndex();
        if (this.sQI.lastIndex() == n) {
            this.sQI.add(Integer.MAX_VALUE);
        }
        int n2 = this.sQI.get(n);
        this.sQI.removeFirst();
        PairsHeap.release(n2);
    }

    private static final void release(int n) {
        if (n != Integer.MAX_VALUE) {
            int n2;
            --used;
            int n3 = n;
            while ((n2 = iB.get(n3)) != Integer.MAX_VALUE) {
                n3 = n2;
                --used;
            }
            iB.put(n3, stackPointer);
            stackPointer = n;
        }
    }

    public static final int used() {
        return used;
    }

    public static final int size() {
        return iB.capacity() / 3;
    }

    public static final void report() {
        logger.info("Max used weights : " + maxSize + " , currently used : " + used + " " + totalUsed12);
    }

    private static final int getFree() {
        ++totalUsed12;
        if (++used > maxSize) {
            maxSize = used;
        }
        int n = stackPointer;
        if ((stackPointer = iB.get(stackPointer)) == Integer.MAX_VALUE) {
            if (n + 3 < iB.capacity()) {
                stackPointer = n + 3;
                iB.put(n + 3, Integer.MAX_VALUE);
            } else {
                stackPointer = iB.capacity();
                PairsHeap.enlarge();
            }
        }
        return n;
    }

    final int getHeapIndex(int n) {
        return n < this.sQI.lastIndex() ? this.sQI.get(n) : Integer.MAX_VALUE;
    }

    final void setHeapIndex(int n, int n2) {
        if (n < this.sQI.lastIndex()) {
            this.sQI.set(n, n2);
        } else {
            do {
                this.sQI.add(Integer.MAX_VALUE);
            } while (this.sQI.lastIndex() <= n);
            this.sQI.set(n, n2);
        }
    }

    public final int getRightmostPoint(int n) {
        if ((n = this.getHeapIndex(n)) == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return iB.get(n + 1);
    }

    public final int getLeftmostPoint(int n) {
        if ((n = this.getHeapIndex(n)) == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        int n2 = iB.get(n + 1);
        while ((n = iB.get(n)) != Integer.MAX_VALUE) {
            n2 = iB.get(n + 1);
        }
        return n2;
    }

    public final int getWeight(int n, int n2, int n3) {
        if ((n = this.getHeapIndex(n)) != Integer.MAX_VALUE) {
            int n4;
            while ((n4 = iB.get(n + 1)) > n2) {
                if ((n = iB.get(n)) != Integer.MAX_VALUE) continue;
                return n3;
            }
            if (n4 == n2) {
                return iB.get(this.weightPointers ? iB.get(n + 2) : n + 2);
            }
        }
        return n3;
    }

    public final int get(int n, int n2, int[] arrn) {
        if (this.weightPointers) {
            n = this.getHeapIndex(n);
            while (n != Integer.MAX_VALUE) {
                arrn[n2] = iB.get(n + 1);
                arrn[n2 + 1] = iB.get(iB.get(n + 2));
                n2 += 2;
                n = iB.get(n);
            }
            return n2;
        }
        n = this.getHeapIndex(n);
        while (n != Integer.MAX_VALUE) {
            arrn[n2] = iB.get(n + 1);
            arrn[n2 + 1] = iB.get(n + 2);
            n2 += 2;
            n = iB.get(n);
        }
        return n2;
    }

    public final int sumTotal(int n, int n2) {
        if (this.weightPointers) {
            if ((n = this.getHeapIndex(n)) != Integer.MAX_VALUE) {
                int n3 = iB.get(iB.get(n + 2));
                n = iB.get(n);
                while (n != Integer.MAX_VALUE) {
                    n3 = Librarian.sum(n3, iB.get(iB.get(n + 2)));
                    n = iB.get(n);
                }
                return n3;
            }
            return n2;
        }
        if ((n = this.getHeapIndex(n)) != Integer.MAX_VALUE) {
            int n4 = iB.get(n + 2);
            n = iB.get(n);
            while (n != Integer.MAX_VALUE) {
                n4 = Librarian.sum(n4, iB.get(n + 2));
                n = iB.get(n);
            }
            return n4;
        }
        return n2;
    }

    private final int addJ(int n, int n2, int n3) {
        int n4;
        int n5 = this.getHeapIndex(n);
        if (n5 == Integer.MAX_VALUE) {
            n5 = PairsHeap.getFree();
            this.setHeapIndex(n, n5);
            iB.put(n5, Integer.MAX_VALUE);
            iB.put(n5 + 1, n2);
            iB.put(n5 + 2, n3);
            return n5;
        }
        int n6 = iB.get(n5 + 1);
        if (n6 < n2) {
            int n7 = PairsHeap.getFree();
            this.setHeapIndex(n, n7);
            iB.put(n7, n5);
            iB.put(n7 + 1, n2);
            iB.put(n7 + 2, n3);
            return n7;
        }
        do {
            n4 = n5;
            if ((n5 = iB.get(n5)) != Integer.MAX_VALUE) continue;
            n5 = PairsHeap.getFree();
            iB.put(n4, n5);
            iB.put(n5, Integer.MAX_VALUE);
            iB.put(n5 + 1, n2);
            iB.put(n5 + 2, n3);
            return n5;
        } while ((n6 = iB.get(n5 + 1)) > n2);
        n6 = PairsHeap.getFree();
        iB.put(n4, n6);
        iB.put(n6, n5);
        iB.put(n6 + 1, n2);
        iB.put(n6 + 2, n3);
        return n6;
    }

    public final int add(int n, int n2, int n3) {
        int n4 = this.getHeapIndex(n);
        if (n4 == Integer.MAX_VALUE) {
            n4 = PairsHeap.getFree();
            this.setHeapIndex(n, n4);
            iB.put(n4, Integer.MAX_VALUE);
            iB.put(n4 + 1, n2);
            this.addWeight(n, n2, n4, n3);
            return n4;
        }
        return this.addI(n, n2, n4, n3);
    }

    private final void addWeight(int n, int n2, int n3, int n4) {
        if (this.weightPointers) {
            int n5 = this.partner.addJ(n2, n, n4) + 2;
            iB.put(n3 + 2, n5);
            return;
        }
        iB.put(n3 + 2, n4);
        this.partner.addJ(n2, n, n3 + 2);
    }

    private final void setWeight(int n, int n2) {
        if (this.weightPointers) {
            int n3 = iB.get(n);
            n2 = Librarian.sum(iB.get(n3), n2);
            iB.put(n3, n2);
            return;
        }
        iB.put(n, Librarian.sum(iB.get(n), n2));
    }

    private final int addI(int n, int n2, int n3, int n4) {
        int n5 = iB.get(n3 + 1);
        if (n5 < n2) {
            int n6 = PairsHeap.getFree();
            this.setHeapIndex(n, n6);
            iB.put(n6, n3);
            iB.put(n6 + 1, n2);
            this.addWeight(n, n2, n6, n4);
            return n6;
        }
        if (n5 > n2) {
            int n7;
            do {
                n7 = n3;
                if ((n3 = iB.get(n3)) != Integer.MAX_VALUE) continue;
                n3 = PairsHeap.getFree();
                iB.put(n7, n3);
                iB.put(n3, Integer.MAX_VALUE);
                iB.put(n3 + 1, n2);
                this.addWeight(n, n2, n3, n4);
                return n3;
            } while ((n5 = iB.get(n3 + 1)) > n2);
            if (n5 == n2) {
                this.setWeight(n3 + 2, n4);
                return n3;
            }
            n5 = PairsHeap.getFree();
            iB.put(n7, n5);
            iB.put(n5, n3);
            iB.put(n5 + 1, n2);
            this.addWeight(n, n2, n5, n4);
            return n5;
        }
        this.setWeight(n3 + 2, n4);
        return n3;
    }

    static {
        maxSize = 0;
        totalUsed12 = 0L;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PairValue
implements Comparable<PairValue> {
    public int seq1;
    public int seq2;
    public int pos1;
    public int pos2;
    public float weight;

    public PairValue(int n, int n2, int n3, int n4, float f) {
        this.seq1 = n;
        this.seq2 = n2;
        this.pos1 = n3;
        this.pos2 = n4;
        this.weight = f;
    }

    @Override
    public int compareTo(PairValue pairValue) {
        if (this.weight < pairValue.weight) {
            return -1;
        }
        if (this.weight > pairValue.weight) {
            return 1;
        }
        return 0;
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.FloatStack;
import bp.common.ds.IntStack;
import bp.common.ds.LockedObject;
import bp.common.ds.ScrollingQueue_Int;
import bp.common.ds.ScrollingQueue_IntTools;
import bp.common.fp.Function;
import bp.common.fp.Function_Index;
import bp.common.fp.Function_Index_2Args;
import bp.common.fp.Function_Index_3Args;
import bp.common.fp.Function_Int;
import bp.common.fp.Function_Int_2Args;
import bp.common.fp.Function_Int_3Args;
import bp.common.fp.Functions_2Args;
import bp.common.fp.Generator;
import bp.common.fp.GeneratorTools;
import bp.common.fp.Generator_Int;
import bp.common.fp.Generators;
import bp.common.fp.Generators_Int;
import bp.common.fp.IterationTools;
import bp.common.fp.Iterators;
import bp.common.fp.Predicate;
import bp.common.fp.Predicate_Double_2Args;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_2Args;
import bp.common.fp.Procedure_Int;
import bp.common.fp.Procedure_Int_2Args;
import bp.common.fp.Procedure_Int_3Args;
import bp.common.fp.Procedure_NoArgs;
import bp.common.fp.Procedures_Int;
import bp.common.io.ArraysToFile;
import bp.common.io.ExternalExecution;
import bp.common.io.FastaOutput_Procedure_Int;
import bp.common.io.FastaParser_Generator_Int;
import bp.common.io.InputMunger;
import bp.common.io.NewickTreeParser;
import bp.common.maths.Maths;
import bp.pecan.AlignmentPump;
import bp.pecan.AlignmentStitcher;
import bp.pecan.AnchorParser_Generator;
import bp.pecan.Cell;
import bp.pecan.Chains;
import bp.pecan.DripAligner;
import bp.pecan.ForwardBackwardMatrixIter;
import bp.pecan.Librarian;
import bp.pecan.MatrixIterator;
import bp.pecan.PairValue;
import bp.pecan.PairsHeap;
import bp.pecan.PecanTools;
import bp.pecan.PolygonFiller;
import bp.pecan.utils.PrePecan;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class Pecan {
    static final Logger logger = Logger.getLogger(Pecan.class.getName());
    static final String HMM = "HMM";
    static final String TRANSITIVE_ANCHORS = "TRANSITIVE_ANCHORS";
    static final String TRANSITIVE_ANCHOR_DIAGONAL_BOUNDARY = "TRANSITIVE_ANCHOR_DIAGONAL_BOUNDARY";
    static final String ANCHOR_DIAGONAL_WIDTH = "ANCHOR_DIAGONAL_WIDTH";
    static final String MINIMUM_SIZE_OF_DIAGONAL_FOR_CUT_POINT = "MINIMUM_SIZE_OF_DIAGONAL_FOR_CUT_POINT";
    static final String MINIMUM_DIAGONAL_GAP_BETWEEN_CUT_POINT_AND_POLYGON = "MINIMUM_DIAGONAL_GAP_BETWEEN_CUT_POINT_AND_POLYGON";
    static final String MINIMUM_GAP_BETWEEN_CUT_POINTS = "MINIMUM_GAP_BETWEEN_CUT_POINTS";
    static final String COMPUTATION_THRESHOLD = "COMPUTATION_THRESHOLD";
    static final String CONSISTENCY_TRANSFORMATION = "CONSISTENCY_TRANSFORMATION";
    static final String USE_DIRECT_BYTE_BUFFERS = "USE_DIRECT_BYTE_BUFFERS";
    static final String MINIMUM_WEIGHT_HEAP_CAPACITY = "MINIMUM_WEIGHT_HEAP_CAPACITY";
    static final String PRE_CLOSE_GAPS_LARGER_THAN = "PRE_CLOSE_GAPS_LARGER_THAN";
    static final String OVERHANGING_DIAGONALS_INTO_PRE_CLOSED_GAPS = "OVERHANGING_DIAGONALS_INTO_PRE_CLOSED_GAPS";
    static final String REORDER_BY_OUTGROUP_DISTANCE = "REORDER_BY_OUTGROUP_DISTANCE";
    static final String USE_HMM_WITH_JUNK_STATE = "USE_HMM_WITH_JUNK_STATE";
    static final String PROVIDE_CONFIDENCE_VALUES = "PROVIDE_CONFIDENCE_VALUES";
    static final String INCLUDE_NOT_ALIGNED_VALUES = "INCLUDE_NOT_ALIGNED_VALUES";
    static final String WRITE_OUT_SEPERATE_CONFIDENCE_VALUES_FILE = "WRITE_OUT_SEPERATE_CONFIDENCE_VALUES_FILE";
    static final String WRITE_CHARACTER_CONFIDENCE_VALUES_IN_MFA = "WRITE_CHARACTER_CONFIDENCE_VALUES_IN_MFA";
    static final String CONFIDENCE_FILE = "CONFIDENCE_FILE";
    static final String MAXIMUM_DIAGONAL_DISTANCE_BETWEEN_RESCALINGS = "MAXIMUM_DIAGONAL_DISTANCE_BETWEEN_RESCALINGS";
    static final String THRESHOLD_WEIGHTS_ACCORDING_TO_GAP_PROBABILITY = "THRESHOLD_WEIGHTS_ACCORDING_TO_GAP_PROBABILITY";
    static final String GAP_GAMMA = "GAP_GAMMA";
    static final String LOAD_CONSTRAINING_ALIGNMENT_FROM_FILE = "LOAD_CONSTRAINING_ALIGNMENT_FROM_FILE";
    static final String CONSTRAINING_ALIGNMENT_FILE_NAME = "CONSTRAINING_ALIGNMENT_FILE_NAME";
    static final String OUTPUT_ALIGNMENT_TO_GAP_PROBABILITIES_TO_FILE = "OUTPUT_ALIGNMENT_TO_GAP_PROBABILITIES_TO_FILE";
    static final String GAP_PROBABILITIES_FILE = "GAP_PROBABILITIES_FILE";
    static final String OUTPUT_TOP_N_ALIGNMENT_PAIRS_TO_FILE = "OUTPUT_ALIGNMENT_PAIRS_TO_FILE";
    static final String MAXIMUM_NUMBER_OF_ALIGNMENT_PAIRS_TO_WRITE_TO_FILE = "MAXIMUM_NUMBER_OF_ALIGNMENT_PAIRS_TO_WRITE_TO_FILE";
    static final String ALIGNMENT_PAIR_PROBABILITIES_FILE = "ALIGNMENT_PAIR_PROBABILITIES_FILE";
    public String hmm = null;
    public String outputFile = "output.mfa";
    public boolean transitiveAnchors = false;
    public int transitiveAnchorDiagonalBoundary = 10;
    public int anchorDiagonalWidth = 10;
    public int minimumSizeOfDiagonalForCutPoint = 4;
    public int minimumDiagonalGapBetweenCutPointAndPolygon = 15;
    public int minimumGapBetweenCutPoints = 500;
    public int maximumDiagonalDistanceBetweenRescalings = 200;
    public int matrixIteratorLineLength = 50000;
    public int matrixStackChunkSize = 1000000;
    public int aboveThresholdStackChunkSize = 5000;
    public int diagonalLineStackChunkSize = 100000;
    public float computationThreshold = 0.01f;
    public boolean consistencyTransformation = true;
    public int diagonalGapBetweenRelations = 10;
    public int scrollingSequenceWindowLength = 5000;
    public int weightTranslatorArrayLength = 8000;
    public int scratchArraySize = 100000;
    public int averageWeightNumber = 5;
    public float expansionRateOfWeightHeap = 1.5f;
    public boolean useDirectByteBuffers = true;
    public int minimumWeightHeapCapacity = 1000000;
    public int preCloseGapsLargerThan = 10000;
    public int overhangingDiagonalsIntoPreClosedGaps = 4500;
    public int reorderByOutgroupDistance = 500;
    public boolean useHMMWithJunkState = true;
    public boolean provideConfidenceValues = false;
    public boolean includeNotAlignedValues = true;
    public boolean writeOutSeperateConfidenceValuesFile = true;
    public boolean writeCharacterConfidenceValuesInMFA = false;
    public String confidenceFile = "confidence.txt";
    public boolean provideMatchStateValues = false;
    public boolean writeOutSeperateMatchStateValuesFile = true;
    public boolean writeCharacterMatchStateValuesInMFA = false;
    public String matchStateFileSuffix = "matchState";
    public boolean thresholdWeightsAccordingToGapProbability = false;
    public float gapGamma = 1.0f;
    public boolean loadConstrainingAlignmentFromFile = false;
    public String constrainingAlignmentFileName = "constraints.txt";
    public boolean outputAlignmentToGapProbabilitiesToFile = false;
    public String gapProbabilitiesFile = "gaps.txt";
    public boolean outputTopNAlignmentPairsToFile = false;
    public int maximumNumberOfAlignmentPairsToWriteToFile = 100000;
    public String alignmentPairProbabilitiesFile = "pairprobs.txt";
    public String[] fastaIDs;
    public int[] seqSizes;
    public byte[][] cachedSeqFiles;

    public void PECANScript(NewickTreeParser.Node node, String[] arrstring, PrePecan prePecan) throws Exception {
        int[] arrn2;
        int n4;
        Cell.GetCellCalculator getCellCalculator5;
        int n;
        int n3;
        Object object;
        Object object2;
        Object[] arrobject;
        Object object3;
        Object arrprocedure_Int;
        Object object4;
        Object object13;
        reference var36_52;
        Object object5;
        Object object6;
        FastaOutput_Procedure_Int[] arrfastaOutput_Procedure_Int;
        float[] arrf2;
        float[] arrf3;
        int arrpairsHeap;
        Object object7;
        Object object8;
        Object object15;
        Object object9;
        Cell.GetCellCalculator getCellCalculator3;
        Cell.GetCellCalculator getCellCalculator4;
        Object object10;
        Cell.GetCellCalculator getCellCalculator2;
        Object object11;
        int n2;
        Object object22;
        LinkedList<Procedure_NoArgs> linkedList;
        Object object12;
        Object object14;
        int[] arrn;
        int n5;
        Function_Int function_Int;
        Object object16;
        ForwardBackwardMatrixIter.AlignmentGenerator alignmentGenerator;
        Object object17;
        Object object18;
        Cell.GetCellCalculator getCellCalculator6;
        int n6;
        Object object19;
        Object object20;
        Cell.GetCellCalculator getCellCalculator;
        int[] arrn3;
        Object[] arrobject2;
        float[] arrf;
        Object object162;
        Object[] arrobject3;
        int n7;
        long l = System.currentTimeMillis();
        int[] arrn4 = new int[this.scratchArraySize];
        if (this.hmm != null) {
            logger.info("HMM model used : " + ExternalExecution.getAbsolutePath(this.hmm));
            arrobject3 = new BufferedReader(new FileReader(this.hmm));
            object22 = new NewickTreeParser(NewickTreeParser.commentEater(NewickTreeParser.tokenise((Reader)arrobject3)));
            arrobject3.close();
            logger.info("Parsed hmm : " + ((NewickTreeParser)object22).tree);
            Cell.isLegitimateHMM(((NewickTreeParser)object22).tree);
            arrobject3 = Cell.createProgram(((NewickTreeParser)object22).tree, Integer.MAX_VALUE, Integer.MAX_VALUE);
            arrf2 = (float[])arrobject3[3];
            arrf3 = (float[])arrobject3[4];
            n7 = arrf2.length;
            logger.info("State number : " + n7);
            n = ((Number)arrobject3[6]).intValue();
            function_Int = (Function_Int)arrobject3[5];
            logger.info("Alphabet size : " + n);
            float[] arrf4 = (float[])arrobject3[2];
            arrn2 = (int[])arrobject3[0];
            float[] arrf5 = (float[])arrobject3[1];
            arrobject = Cell.makeRProgram(arrn2, arrf5, n7);
            arrn3 = (int[])arrobject[0];
            object162 = (float[])arrobject[1];
            arrn = (int[])arrn2.clone();
            int[] arrn5 = (int[])arrn2.clone();
            Cell.transformProgram(arrn, -(this.matrixIteratorLineLength + 1), arrf2.length);
            Cell.transformProgram(arrn5, this.matrixIteratorLineLength + 1, arrf2.length);
            arrobject = Cell.makeRProgram(arrn, arrf5, arrf2.length);
            object15 = (int[])arrobject[0];
            arrobject = Cell.makeRProgram(arrn5, arrf5, arrf2.length);
            object13 = (int[])arrobject[0];
            getCellCalculator3 = Cell.getForwardCellCalculator(arrn2, arrf5, arrf4, n);
            getCellCalculator = Cell.getForwardCellCalculator(arrn, arrf5, arrf4, n);
            getCellCalculator2 = Cell.getForwardCellCalculator(arrn5, arrf5, arrf4, n);
            getCellCalculator5 = Cell.getBackwardCellCalculator(arrn3, object162, arrf4, arrf2.length, n);
            getCellCalculator4 = Cell.getBackwardCellCalculator((int[])object15, object162, arrf4, arrf2.length, n);
            getCellCalculator6 = Cell.getBackwardCellCalculator((int[])object13, object162, arrf4, arrf2.length, n);
        } else {
            object22 = this.useHMMWithJunkState ? new Cell.FiveCell() : new Cell.ThreeCell();
            logger.info("Using default hmm");
            arrf2 = ((Cell.BasicCell)object22).startStates;
            arrf3 = ((Cell.BasicCell)object22).endStates;
            n7 = ((Cell.BasicCell)object22).stateNumber;
            n = ((Cell.BasicCell)object22).alphabetSize;
            function_Int = ((Cell.BasicCell)object22).translateChars;
            getCellCalculator3 = ((Cell.BasicCell)object22).getForwardCellCalculator(n7, n7 * 2, n7 * 3);
            getCellCalculator5 = ((Cell.BasicCell)object22).getBackwardCellCalculator(n7, n7 * 2, n7 * 3);
            getCellCalculator = ((Cell.BasicCell)object22).getForwardCellCalculator(-n7, this.matrixIteratorLineLength * n7, (this.matrixIteratorLineLength + 1) * n7);
            getCellCalculator4 = ((Cell.BasicCell)object22).getBackwardCellCalculator(-n7, this.matrixIteratorLineLength * n7, (this.matrixIteratorLineLength + 1) * n7);
            getCellCalculator2 = ((Cell.BasicCell)object22).getForwardCellCalculator(-n7, -(this.matrixIteratorLineLength + 2) * n7, -(this.matrixIteratorLineLength + 1) * n7);
            getCellCalculator6 = ((Cell.BasicCell)object22).getBackwardCellCalculator(-n7, -(this.matrixIteratorLineLength + 2) * n7, -(this.matrixIteratorLineLength + 1) * n7);
        }
        PecanTools.replaceEdgeLengths(node, Double.MIN_VALUE, 1.0);
        logger.info("Parsed tree : " + node);
        int n9 = arrstring.length;
        arrobject3 = PecanTools.getDistances(node, n9, Functions_2Args.sum());
        for (int i = 0; i < arrobject3.length; ++i) {
            logger.info("Sequence distances for " + i + " " + IterationTools.join((double[])arrobject3[i], " "));
        }
        int[][][] arrn6 = PecanTools.getOutgroups(new LockedObject(arrn4), (double[][])arrobject3, n9);
        arrn2 = PecanTools.getPairOrdering((double[][])arrobject3);
        for (int i = 0; i < arrn6.length; ++i) {
            for (int j = i + 1; j < arrn6.length; ++j) {
                logger.info(" Sequence outgroups for " + i + " , " + j + " : " + IterationTools.join(arrn6[i][j], " "));
            }
        }
        String[] arrstring2 = new String[n9];
        arrobject = new int[n9][2];
        arrn3 = new Generator[n9][n9][2];
        arrn = new Generator[n9][n9][3];
        if (this.loadConstrainingAlignmentFromFile) {
            object15 = Chains.loadConstraintsFromAlignment(this.constrainingAlignmentFileName, n9);
            object13 = prePecan.getSeqSizes(arrstring);
            this.fastaIDs = (String[])object13.get(0);
            this.seqSizes = (int[])object13.get(1);
            this.cachedSeqFiles = (byte[][])object13.get(2);
        } else {
            object15 = prePecan.runPrePecan(node, arrstring);
            this.fastaIDs = prePecan.fastaIDs;
            this.seqSizes = prePecan.seqSizes;
            this.cachedSeqFiles = prePecan.cachedSeqFiles;
        }
        for (int i = 0; i < n9; ++i) {
            int n10 = this.seqSizes[i];
            for (n4 = i + 1; n4 < n9; ++n4) {
                n6 = this.seqSizes[n4];
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints(i, n4, -11, -11, 10);
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints(n4, i, -11, -11, 10);
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints(i, n4, n10, n6, 0);
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints(n4, i, n6, n10, 0);
            }
        }
        List[][] arrlist = new List[n9][n9];
        Object object25 = new List[n9][n9];
        for (n4 = 0; n4 < n9; ++n4) {
            n6 = n4 + 1;
            while (n6 < n9) {
                var36_52 = (List)GeneratorTools.append(((Chains.PrimeConstraints)object15).convertPrimeConstraintsToEdgeList(n4, n6, true), new LinkedList());
                arrlist[n4][n6] = var36_52;
                arrpairsHeap = n4;
                object20 = n6++;
                object25[n4][n6] = (List)GeneratorTools.append(Generators.map(Generators.filter(PolygonFiller.cutPointGenerator(Generators.iteratorGenerator(var36_52.iterator()), this.minimumSizeOfDiagonalForCutPoint, 100), new Predicate(){
                    int pDiagonal = 0;

                    public boolean test(Object object) {
                        int[] arrn = (int[])object;
                        if (arrn[0] + arrn[1] > this.pDiagonal + Pecan.this.minimumGapBetweenCutPoints) {
                            this.pDiagonal = arrn[0] + arrn[1];
                            return true;
                        }
                        return false;
                    }
                }), new Function((int)object20){
                    final /* synthetic */ int val$m;
                    {
                        this.val$m = n2;
                    }

                    public Object fn(Object object) {
                        int[] arrn = (int[])object;
                        return new Chains.CutPoint(arrpairsHeap, this.val$m, arrn[0], arrn[1], Pecan.this.minimumDiagonalGapBetweenCutPointAndPolygon);
                    }
                }), new LinkedList());
            }
        }
        Chains.LocalAligner localAligner = PrePecan.makeExonerateAligner(prePecan.exonerateBasicCommand, prePecan.exonerateString, prePecan.exoneratePartitionAlignerWordLength, prePecan.exoneratePartitionAlignerMinScore, prePecan.exoneratePartitionAlignerGappedExtension, prePecan.exoneratePartitionAlignerModel, prePecan.saturateThreshold, prePecan.exoneratePartitionAlignerSoftMaskSequences, prePecan.exoneratePartitionAlignerSoftMaskSequences);
        if (prePecan.rescoreAlignments) {
            localAligner = Chains.rescoreAlignments(localAligner, prePecan.relativeEntropyThreshold);
        }
        Chains.Aligner aligner = Chains.alignerConvertor(localAligner);
        Chains.transformConstraintsAndCloseLargeGaps((Chains.PrimeConstraints)object15, arrlist, this.anchorDiagonalWidth, this.preCloseGapsLargerThan, this.overhangingDiagonalsIntoPreClosedGaps, aligner, this.cachedSeqFiles, (int[][])arrn2);
        for (n5 = 0; n5 < n9; ++n5) {
            int n11 = this.seqSizes[n5];
            for (var36_52 = (reference)(n5 + 1); var36_52 < n9; ++var36_52) {
                arrpairsHeap = this.seqSizes[var36_52];
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints(n5, (int)var36_52, -11, -11, 10);
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints((int)var36_52, n5, -11, -11, 10);
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints(n5, (int)var36_52, n11, arrpairsHeap, 0);
                ((Chains.PrimeConstraints)object15).updatePrimeConstraints((int)var36_52, n5, arrpairsHeap, n11, 0);
            }
        }
        n5 = 0;
        for (n3 = 0; n3 < n9; ++n3) {
            arrobject[n3][0] = n5;
            arrobject[n3][1] = n5 += this.seqSizes[n3];
            arrstring2[n3] = this.fastaIDs[n3];
        }
        ScrollingQueue_Int[] arrscrollingQueue_Int = new Function(){

            public Object fn(Object object) {
                PolygonFiller.Node node = (PolygonFiller.Node)object;
                return new int[]{node.x, node.y};
            }
        };
        for (n3 = 0; n3 < n9; ++n3) {
            var36_52 = arrobject[n3][0];
            for (arrpairsHeap = n3 + 1; arrpairsHeap < n9; ++arrpairsHeap) {
                int n8;
                object20 = arrobject[arrpairsHeap][0];
                arrprocedure_Int = new ByteArrayOutputStream();
                object14 = (LinkedList)GeneratorTools.append(PolygonFiller.transformEdges(PolygonFiller.flipEdgeXYDiagonalsCoordinates(((Chains.PrimeConstraints)object15).convertPrimeConstraintsToEdgeList(arrpairsHeap, n3, false)), (int)var36_52, object20), new LinkedList());
                int n10 = AnchorParser_Generator.writeOutEdgeList(Generators.filter(Generators.iteratorGenerator(object14.iterator()), PolygonFiller.isLessThanOrEqual()), (OutputStream)arrprocedure_Int);
                Object object21 = AnchorParser_Generator.readInEdgeList(new BufferedInputStream(new ByteArrayInputStream(((ByteArrayOutputStream)arrprocedure_Int).toByteArray())), n10);
                object6 = Generators.splitGenerator((Generator)object21, PolygonFiller.cloneEdge());
                arrn3[n3][arrpairsHeap][0] = object6[0];
                arrn3[n3][arrpairsHeap][1] = object6[1];
                object21 = new ByteArrayOutputStream();
                n10 = ArraysToFile.writeOutArray(Generators.map(Generators.filter(Generators.iteratorGenerator(object14.iterator()), PolygonFiller.isLessThan()), (Function)arrscrollingQueue_Int), (OutputStream)object21);
                object6 = ((ByteArrayOutputStream)object21).toByteArray();
                for (n8 = 0; n8 < 3; ++n8) {
                    arrn[n3][arrpairsHeap][n8] = ArraysToFile.readInArray(new ByteArrayInputStream((byte[])object6), n10, 2);
                }
                arrprocedure_Int = new ByteArrayOutputStream();
                object14 = (List)GeneratorTools.append(PolygonFiller.transformEdges(PolygonFiller.flipEdgeXYDiagonalsCoordinates(((Chains.PrimeConstraints)object15).convertPrimeConstraintsToEdgeList(n3, arrpairsHeap, false)), object20, (int)var36_52), new LinkedList());
                n10 = AnchorParser_Generator.writeOutEdgeList(Generators.filter(Generators.iteratorGenerator(object14.iterator()), PolygonFiller.isLessThanOrEqual()), (OutputStream)arrprocedure_Int);
                object21 = AnchorParser_Generator.readInEdgeList(new BufferedInputStream(new ByteArrayInputStream(((ByteArrayOutputStream)arrprocedure_Int).toByteArray())), n10);
                object6 = Generators.splitGenerator((Generator)object21, PolygonFiller.cloneEdge());
                arrn3[arrpairsHeap][n3][0] = object6[0];
                arrn3[arrpairsHeap][n3][1] = object6[1];
                object21 = new ByteArrayOutputStream();
                n10 = ArraysToFile.writeOutArray(Generators.map(Generators.filter(Generators.iteratorGenerator(object14.iterator()), PolygonFiller.isLessThan()), (Function)arrscrollingQueue_Int), (OutputStream)object21);
                object6 = ((ByteArrayOutputStream)object21).toByteArray();
                for (n8 = 0; n8 < 3; ++n8) {
                    arrn[arrpairsHeap][n3][n8] = ArraysToFile.readInArray(new ByteArrayInputStream((byte[])object6), n10, 2);
                }
            }
        }
        arrscrollingQueue_Int = new Comparator(){

            public int compare(Object object, Object object2) {
                Chains.CutPoint cutPoint = (Chains.CutPoint)object;
                Chains.CutPoint cutPoint2 = (Chains.CutPoint)object2;
                int n = cutPoint.x + cutPoint.y;
                int n2 = cutPoint2.x + cutPoint2.y;
                return n < n2 ? -1 : (n > n2 ? 1 : 0);
            }
        };
        List[][] arrlist2 = new List[n9][n9];
        List[][] object27 = new List[n9][n9];
        for (arrpairsHeap = 0; arrpairsHeap < n9; ++arrpairsHeap) {
            for (object20 = arrpairsHeap + 1; object20 < n9; ++object20) {
                arrlist2[arrpairsHeap][object20] = arrprocedure_Int = Chains.breaks((Chains.PrimeConstraints)object15, arrpairsHeap, object20);
                object14 = new LinkedList();
                object27[arrpairsHeap][object20] = object14;
                List list = object25[arrpairsHeap][object20];
                Iterator iterator = arrprocedure_Int.iterator();
                while (iterator.hasNext()) {
                    object6 = (int[])iterator.next();
                    object14.add(new Chains.CutPoint(arrpairsHeap, (int)object20, (int)object6[0], (int)object6[1], Integer.MAX_VALUE));
                }
                object25[arrpairsHeap][object20] = (List)IterationTools.append(Iterators.merge(Chains.filterCutPoints(list, (List)arrprocedure_Int, this.minimumDiagonalGapBetweenCutPointAndPolygon + 2), object14.iterator(), (Comparator)arrscrollingQueue_Int), new LinkedList());
                Object object23 = Integer.MIN_VALUE;
                if (arrprocedure_Int.size() != 0) {
                    object6 = (int[])arrprocedure_Int.get(arrprocedure_Int.size() - 1);
                    object23 = object6[2] + object6[3] + 2;
                }
                if (object23 >= this.seqSizes[arrpairsHeap] + this.seqSizes[object20]) continue;
                arrprocedure_Int.add(new int[]{0, 0, this.seqSizes[arrpairsHeap] - 1, this.seqSizes[object20]});
                object6 = new Chains.CutPoint(arrpairsHeap, (int)object20, this.seqSizes[arrpairsHeap] - 1, this.seqSizes[object20] - 1, Integer.MAX_VALUE);
                object14.add(object6);
                object25[arrpairsHeap][object20].add(object6);
            }
        }
        List list = Chains.CutPointOrdering.orderCutPoints((Chains.PrimeConstraints)object15, object25);
        list = Chains.CutPointReordering.reorder(list, n9, arrn6, (int[][])arrn2, this.reorderByOutgroupDistance * (n9 * (n9 - 1) / 2), -10);
        for (object20 = 0; object20 < n9; ++object20) {
            for (int i = object20 + 1; i < n9; ++i) {
                object14 = arrlist2[object20][i].iterator();
                Iterator iterator = object27[object20][i].iterator();
                while (object14.hasNext()) {
                    object17 = (int[])object14.next();
                    object6 = (Chains.CutPoint)iterator.next();
                    ((Chains.CutPoint)object6).x = object17[2] + 1;
                    ((Chains.CutPoint)object6).y = object17[3];
                }
                if (!iterator.hasNext()) continue;
                throw new IllegalStateException();
            }
        }
        for (Chains.CutPoint cutPoint : list) {
            cutPoint.x += arrobject[cutPoint.s1][0];
            cutPoint.y += arrobject[cutPoint.s2][0];
        }
        ScrollingQueue_Int[][] arrscrollingQueue_Int2 = PecanTools.getTempFileHandle();
        Procedure_Int[][] arrprocedure_Int2 = new BufferedOutputStream(new FileOutputStream((String)arrscrollingQueue_Int2));
        int n11 = ArraysToFile.writeOutArray(Generators.map(Generators.iteratorGenerator(list.iterator()), new Function(){

            public Object fn(Object object) {
                Chains.CutPoint cutPoint = (Chains.CutPoint)object;
                return new int[]{cutPoint.s1, cutPoint.s2, cutPoint.x, cutPoint.y, cutPoint.tB};
            }
        }), (OutputStream)arrprocedure_Int2);
        arrprocedure_Int2.close();
        object162 = Generators.map(ArraysToFile.readInArray(new BufferedInputStream(new FileInputStream((String)arrscrollingQueue_Int2)), n11, 5), new Function(){

            public Object fn(Object object) {
                int[] arrn = (int[])object;
                return new Chains.CutPoint(arrn[0], arrn[1], arrn[2], arrn[3], arrn[4]);
            }
        });
        prePecan = null;
        object15 = new Generator_Int[arrstring.length];
        for (int i = 0; i < n9; ++i) {
            object25 = new FastaParser_Generator_Int(new BufferedInputStream(new FileInputStream(arrstring[i])), 78);
            arrscrollingQueue_Int = function_Int;
            object15[i] = new Generator_Int((Function_Int)arrscrollingQueue_Int, (FastaParser_Generator_Int)object25){
                final /* synthetic */ Function_Int val$fn;
                final /* synthetic */ FastaParser_Generator_Int val$gen2;
                {
                    this.val$fn = function_Int;
                    this.val$gen2 = fastaParser_Generator_Int;
                }

                public int gen() {
                    return this.val$fn.fn(this.val$gen2.gen());
                }
            };
            logger.info("Seq " + arrstring[i] + " start : " + (int)arrobject[i][0]);
            logger.info("Seq " + arrstring[i] + " end : " + (int)arrobject[i][1]);
        }
        Function_Int[] arrfunction_Int = new Function_Int[n9];
        object25 = new Procedure_Int[n9];
        arrscrollingQueue_Int = new ScrollingQueue_Int[n9];
        for (int i = 0; i < n9; ++i) {
            ScrollingQueue_Int scrollingQueue_Int = new ScrollingQueue_Int(this.scrollingSequenceWindowLength, (int)(arrobject[i][0] - true), true);
            scrollingQueue_Int.add(4);
            arrscrollingQueue_Int[i] = scrollingQueue_Int;
            arrfunction_Int[i] = scrollingQueue_Int;
            object25[i] = ScrollingQueue_IntTools.fillFromGenerator((Generator_Int)object15[i], scrollingQueue_Int);
        }
        Function[][] arrfunction = new Function[n9][n9];
        boolean[][] arrbl = new boolean[n9][n9];
        System.gc();
        int n12 = this.averageWeightNumber * (n9 * n9 - n9) * 2 * this.scrollingSequenceWindowLength * 12;
        if (n12 < this.minimumWeightHeapCapacity) {
            n12 = this.minimumWeightHeapCapacity;
        }
        PairsHeap.initialise(n12, this.expansionRateOfWeightHeap, this.useDirectByteBuffers);
        final PairsHeap[][] arrpairsHeap2 = new PairsHeap[n9][n9];
        arrscrollingQueue_Int2 = new ScrollingQueue_Int[n9][n9];
        arrprocedure_Int2 = new Procedure_Int[n9][n9];
        for (n11 = 0; n11 < n9; ++n11) {
            for (int i = n11 + 1; i < n9; ++i) {
                object17 = PairsHeap.getPair((int)arrobject[n11][0], (int)arrobject[i][0], this.scrollingSequenceWindowLength);
                arrpairsHeap2[n11][i] = (PairsHeap)object17[0];
                arrpairsHeap2[i][n11] = (PairsHeap)object17[1];
                arrscrollingQueue_Int2[n11][i] = new ScrollingQueue_Int(this.scrollingSequenceWindowLength, (int)arrobject[n11][0], true);
                arrscrollingQueue_Int2[i][n11] = new ScrollingQueue_Int(this.scrollingSequenceWindowLength, (int)arrobject[i][0], true);
                arrprocedure_Int2[n11][i] = ScrollingQueue_IntTools.fillFromGenerator(Generators_Int.constant(Integer.MAX_VALUE), arrscrollingQueue_Int2[n11][i]);
                arrprocedure_Int2[i][n11] = ScrollingQueue_IntTools.fillFromGenerator(Generators_Int.constant(Integer.MAX_VALUE), arrscrollingQueue_Int2[i][n11]);
            }
        }
        MatrixIterator matrixIterator = new MatrixIterator(n7, this.matrixIteratorLineLength);
        FloatStack floatStack = new FloatStack(this.matrixStackChunkSize);
        object17 = new IntStack(this.aboveThresholdStackChunkSize);
        object6 = new FloatStack(this.diagonalLineStackChunkSize);
        float[] arrf4 = new float[this.matrixIteratorLineLength * 2 * n7];
        float[] arrf5 = new float[this.matrixIteratorLineLength * 2 * n7];
        for (int i = 0; i < n9; ++i) {
            arrfastaOutput_Procedure_Int = arrfunction_Int[i];
            arrobject2 = object25[i];
            object3 = arrobject[i][0];
            int n13 = i + 1;
            while (n13 < n9) {
                Object object24;
                Object object26;
                linkedList = arrobject[n13][0];
                object11 = arrn6[i][n13].length;
                if (this.transitiveAnchors && object11 > 0) {
                    arrf = PecanTools.transitiveAnchors(this.transitiveAnchorDiagonalBoundary, arrscrollingQueue_Int2[i][n13]);
                    object24 = PecanTools.transitiveAnchors(this.transitiveAnchorDiagonalBoundary, arrscrollingQueue_Int2[n13][i]);
                    object2 = object26 = new PecanTools.CutUpDiagonals((Generator)arrn3[i][n13][0]);
                    object10 = object7 = new PecanTools.CutUpDiagonals((Generator)arrn3[n13][i][0]);
                    object19 = PolygonFiller.mergeEdgeListWithTransitiveAnchors((Generator)object26, (Function_Index_2Args)object24, PolygonFiller.filterEdgeListByLessThanConstraints((Generator)arrn[n13][i][0], this.anchorDiagonalWidth), PolygonFiller.filterEdgeListByLessThanOrEqualConstraints((Generator)arrn3[n13][i][1], this.anchorDiagonalWidth));
                    object12 = PolygonFiller.mergeEdgeListWithTransitiveAnchors((Generator)object7, (Function_Index_2Args)arrf, PolygonFiller.filterEdgeListByLessThanConstraints((Generator)arrn[i][n13][0], this.anchorDiagonalWidth), PolygonFiller.filterEdgeListByLessThanOrEqualConstraints((Generator)arrn3[i][n13][1], this.anchorDiagonalWidth));
                } else {
                    object2 = Procedures_Int.doNothing();
                    object10 = Procedures_Int.doNothing();
                    arrn[i][n13][0] = null;
                    arrn[n13][i][0] = null;
                    object19 = arrn3[i][n13][0];
                    object12 = arrn3[n13][i][0];
                }
                arrf = new Function(){

                    public final Object fn(Object object) {
                        int[] arrn = (int[])object;
                        int n = arrn[0];
                        int n2 = arrn[1];
                        return new PolygonFiller.Node(n, n2, n2, 0);
                    }
                };
                object18 = PolygonFiller.polygonIterator(PolygonFiller.mergeLessThansEdgeList((Generator)object19, Generators.map((Generator)arrn[i][n13][2], (Function)arrf)), PolygonFiller.mergeLessThansEdgeList((Generator)object12, Generators.map((Generator)arrn[n13][i][2], (Function)arrf)), object3 - 10, 1073741823, (int)(linkedList - 10), 1073741823);
                object18 = PolygonFiller.polygonIteratorWithLessThans((Function_Index)object18, (Generator)arrn[i][n13][1], (Generator)arrn[n13][i][1], arrn4);
                arrf = new float[(((this.anchorDiagonalWidth + this.minimumDiagonalGapBetweenCutPointAndPolygon) * 2 + 1) * 2 + 1) * n7];
                Arrays.fill(arrf, Float.NEGATIVE_INFINITY);
                System.arraycopy(arrf2, 0, arrf, arrf2.length, arrf2.length);
                object24 = arrfunction_Int[n13];
                object26 = getCellCalculator3.getCellCalculator((Function_Int)arrfastaOutput_Procedure_Int, (Function_Int)object24);
                object7 = getCellCalculator.getCellCalculator((Function_Int)arrfastaOutput_Procedure_Int, (Function_Int)object24);
                object9 = getCellCalculator2.getCellCalculator((Function_Int)arrfastaOutput_Procedure_Int, (Function_Int)object24);
                object8 = getCellCalculator5.getCellCalculator((Function_Int)arrfastaOutput_Procedure_Int, (Function_Int)object24);
                object5 = getCellCalculator4.getCellCalculator((Function_Int)arrfastaOutput_Procedure_Int, (Function_Int)object24);
                Cell.CellCalculator cellCalculator = getCellCalculator6.getCellCalculator((Function_Int)arrfastaOutput_Procedure_Int, (Function_Int)object24);
                alignmentGenerator = new ForwardBackwardMatrixIter.AlignmentGenerator(arrf2.length, 0, Maths.log(this.computationThreshold), floatStack, (Procedure_Int)object17);
                ForwardBackwardMatrixIter object32 = new ForwardBackwardMatrixIter((Function_Index)object18, arrf2.length, object3 - 1, (int)(linkedList - true), 0, arrf, arrf4, arrf5, arrf3, (Cell.CellCalculator)object26, (Cell.CellCalculator)object8, (Cell.CellCalculator)object7, (Cell.CellCalculator)object9, (Cell.CellCalculator)object5, cellCalculator, alignmentGenerator.forwards, alignmentGenerator.backwards, alignmentGenerator.passRunningTotal, matrixIterator, (FloatStack)object6, this.maximumDiagonalDistanceBetweenRescalings, alignmentGenerator.reset);
                object4 = ForwardBackwardMatrixIter.cutPointAlignmentGenerator(object32);
                object16 = object25[n13];
                object = i;
                n2 = n13++;
                Object object28 = object18;
                arrfunction[i][n13] = new Function((Procedure_Int)object10, (Procedure_Int)object2, arrbl, (int)object, n2, (Function_Index)object28, (int)object3, (int)linkedList, (Procedure_Int)arrobject2, (Procedure_Int)object16, (Function_Index_3Args)object4){
                    final /* synthetic */ Procedure_Int val$edgeListColumnsCutUpFeeder;
                    final /* synthetic */ Procedure_Int val$edgeListRowsCutUpFeeder;
                    final /* synthetic */ boolean[][] val$pairStarts;
                    final /* synthetic */ int val$m;
                    final /* synthetic */ int val$n;
                    final /* synthetic */ Function_Index val$fn2;
                    final /* synthetic */ int val$xStart;
                    final /* synthetic */ int val$yStart;
                    final /* synthetic */ Procedure_Int val$seqAddI;
                    final /* synthetic */ Procedure_Int val$seqAddJ;
                    final /* synthetic */ Function_Index_3Args val$fnFB;
                    {
                        this.val$edgeListColumnsCutUpFeeder = procedure_Int;
                        this.val$edgeListRowsCutUpFeeder = procedure_Int2;
                        this.val$pairStarts = arrbl;
                        this.val$m = n;
                        this.val$n = n2;
                        this.val$fn2 = function_Index;
                        this.val$xStart = n3;
                        this.val$yStart = n4;
                        this.val$seqAddI = procedure_Int3;
                        this.val$seqAddJ = procedure_Int4;
                        this.val$fnFB = function_Index_3Args;
                    }

                    public Object fn(Object object) {
                        Chains.CutPoint cutPoint = (Chains.CutPoint)object;
                        int n = cutPoint.x + cutPoint.y;
                        this.val$edgeListColumnsCutUpFeeder.pro(n);
                        this.val$edgeListRowsCutUpFeeder.pro(n);
                        if (!this.val$pairStarts[this.val$m][this.val$n]) {
                            this.val$fn2.fn(this.val$xStart - 1 + this.val$yStart - 1);
                            this.val$pairStarts[this.val$m][this.val$n] = true;
                            if (cutPoint.x == this.val$xStart && cutPoint.y == this.val$yStart) {
                                return new int[]{this.val$xStart - 1, this.val$yStart - 1, this.val$xStart - 1, this.val$yStart - 1, this.val$xStart - 1, this.val$yStart - 1, this.val$xStart - 1, this.val$yStart - 1};
                            }
                        }
                        logger.info(" Actually doing " + cutPoint);
                        int n2 = cutPoint.x + Pecan.this.anchorDiagonalWidth + 2;
                        this.val$seqAddI.pro(n2);
                        n2 = cutPoint.y + Pecan.this.anchorDiagonalWidth + 2;
                        this.val$seqAddJ.pro(n2);
                        Object object2 = this.val$fnFB.fn(cutPoint.x, cutPoint.y, cutPoint.tB);
                        return object2;
                    }
                };
            }
        }
        File[] arrfile = new File[n9];
        arrfastaOutput_Procedure_Int = new FastaOutput_Procedure_Int[n9];
        arrobject2 = new Procedure_Int[n9];
        for (object3 = 0; object3 < arrstring2.length; ++object3) {
            String string = arrstring2[object3];
            linkedList = File.createTempFile("tmp_output_", ".fa");
            ((File)((Object)linkedList)).deleteOnExit();
            logger.info(" Temp output file for : " + string + " is : " + ((File)((Object)linkedList)).toString());
            arrfile[object3] = linkedList;
            arrfastaOutput_Procedure_Int[object3] = new FastaOutput_Procedure_Int(new BufferedOutputStream(new FileOutputStream((File)((Object)linkedList))), string);
            object11 = new FastaParser_Generator_Int(new BufferedInputStream(new FileInputStream(arrstring[object3])), Integer.MAX_VALUE);
            object18 = arrfastaOutput_Procedure_Int[object3];
            arrobject2[object3] = new Procedure_Int((Procedure_Int)object18, (FastaParser_Generator_Int)object11){
                final /* synthetic */ Procedure_Int val$outputFn;
                final /* synthetic */ FastaParser_Generator_Int val$fPGI;
                {
                    this.val$outputFn = procedure_Int;
                    this.val$fPGI = fastaParser_Generator_Int;
                }

                public void pro(int n) {
                    this.val$outputFn.pro(n == 45 ? 45 : this.val$fPGI.gen());
                }
            };
        }
        Procedure procedure = new Procedure((int[][])arrobject, n9, (Procedure_Int[])arrobject2, arrscrollingQueue_Int, arrscrollingQueue_Int2){
            Procedure_2Args convertInput = AlignmentStitcher.convertInput();
            Librarian.FreeMemory freeMem = new Librarian.FreeMemory(arrpairsHeap2, this.val$seqStartAndEnds, this.val$sequenceNumber);
            int upto = 0;
            int[] uptoA = new int[this.val$sequenceNumber];
            final /* synthetic */ int[][] val$seqStartAndEnds;
            final /* synthetic */ int val$sequenceNumber;
            final /* synthetic */ Procedure_Int[] val$outputFns;
            final /* synthetic */ ScrollingQueue_Int[] val$sequences;
            final /* synthetic */ ScrollingQueue_Int[][] val$maxOffsets;
            {
                this.val$seqStartAndEnds = arrn;
                this.val$sequenceNumber = n;
                this.val$outputFns = arrprocedure_Int;
                this.val$sequences = arrscrollingQueue_Int;
                this.val$maxOffsets = arrscrollingQueue_Int2;
                for (int i = 0; i < this.val$sequenceNumber; ++i) {
                    this.uptoA[i] = this.val$seqStartAndEnds[i][0] - 1;
                }
            }

            public void pro(Object object) {
                Iterator iterator = ((List)object).iterator();
                if (iterator.hasNext()) {
                    int n;
                    do {
                        ++this.upto;
                        int[] arrn = new int[this.val$sequenceNumber];
                        this.convertInput.pro(iterator.next(), arrn);
                        for (n = 0; n < arrn.length; ++n) {
                            int n2 = arrn[n];
                            if (n2 != Integer.MAX_VALUE) {
                                int n3;
                                this.uptoA[n] = n2;
                                this.val$outputFns[n].pro(0);
                                this.val$sequences[n].removeFirst();
                                ScrollingQueue_Int[] arrscrollingQueue_Int = this.val$maxOffsets[n];
                                for (n3 = 0; n3 < n; ++n3) {
                                    arrscrollingQueue_Int[n3].removeFirst();
                                }
                                for (n3 = n + 1; n3 < this.val$sequenceNumber; ++n3) {
                                    arrscrollingQueue_Int[n3].removeFirst();
                                }
                                if (this.val$sequences[n].firstIndex() != n2) {
                                    throw new IllegalStateException();
                                }
                                for (n3 = 0; n3 < this.val$sequenceNumber; ++n3) {
                                    if (n3 == n || arrscrollingQueue_Int[n3].firstIndex() == n2 + 1) continue;
                                    throw new IllegalStateException(arrscrollingQueue_Int[n3].firstIndex() + " " + n2 + " ");
                                }
                                continue;
                            }
                            this.val$outputFns[n].pro(45);
                        }
                    } while (iterator.hasNext());
                    for (n = 0; n < this.val$sequenceNumber; ++n) {
                        this.freeMem.pro(n, this.uptoA[n]);
                    }
                    for (n = 0; n < this.val$sequenceNumber; ++n) {
                        this.freeMem.pro(n);
                    }
                }
            }
        };
        Function_Int_2Args function_Int_2Args = new Function_Int_2Args(){

            public int fn(int n, int n2) {
                return Librarian.sum(n, n2);
            }
        };
        linkedList = new LinkedList<Procedure_NoArgs>();
        if (this.provideConfidenceValues) {
            object11 = new LinkedList();
            if (this.writeCharacterConfidenceValuesInMFA) {
                PecanTools.outputTrack((List<Procedure_Int>)object11, linkedList, this.outputFile, "confidence_values");
            }
            if (this.writeOutSeperateConfidenceValuesFile) {
                PecanTools.outputFloatFile((List<Procedure_Int>)object11, linkedList, this.confidenceFile);
            }
            object18 = this.includeNotAlignedValues ? PecanTools.totalScoreMinus(PecanTools.getWeight(arrpairsHeap2), PecanTools.sumTotal(arrpairsHeap2)) : PecanTools.totalScore(PecanTools.getWeight(arrpairsHeap2));
            procedure = PecanTools.outputTrackAdaptor((Function)object18, n9, (List<Procedure_Int>)object11, procedure);
        }
        object11 = null;
        if (this.outputAlignmentToGapProbabilitiesToFile) {
            object11 = PecanTools.getPrintWriter(this.gapProbabilitiesFile);
            procedure = PecanTools.outputGapAlignments(arrpairsHeap2, n9, procedure, (PrintWriter)object11);
        }
        object18 = new PriorityQueue();
        object2 = new ArrayList();
        object2.add(new Integer(0));
        if (this.outputTopNAlignmentPairsToFile) {
            procedure = PecanTools.outputAlignmentPairs(arrpairsHeap2, n9, procedure, (PriorityQueue<PairValue>)object18, (List<Integer>)object2, this.maximumNumberOfAlignmentPairsToWriteToFile);
        }
        object10 = Librarian.weightsGetter(arrpairsHeap2);
        object19 = this.consistencyTransformation ? Librarian.weightsGetter_Consistency(arrpairsHeap2) : (this.thresholdWeightsAccordingToGapProbability ? Librarian.weightsGetter_FilterByGapThreshold(arrpairsHeap2, this.gapGamma) : object10);
        object12 = Librarian.getMaxResidue(arrscrollingQueue_Int2);
        arrf = new Procedure_Int_3Args(){

            public void pro(int n, int n2, int n3) {
            }
        };
        object7 = new DripAligner.Add(){

            public double fn(double d, int n) {
                double d2 = d + (double)Float.intBitsToFloat(n);
                if (d2 == d) {
                    throw new IllegalStateException(d + " " + d2 + " " + Float.intBitsToFloat(n));
                }
                return d2;
            }
        };
        object9 = new Predicate_Double_2Args(){

            public boolean test(double d, double d2) {
                return d > d2;
            }
        };
        object8 = new Procedure[n9];
        AlignmentPump.getAlignmentPumps(procedure, (Librarian.WeightsGetter)object19, (Function_Int_3Args)object12, (int[][])arrobject, object8, 0, node, this.weightTranslatorArrayLength, function_Int_2Args, (DripAligner.Add)object7, (Predicate_Double_2Args)object9, 0.0, (Procedure_Int_3Args)arrf, arrn4);
        object5 = new Procedure_Int[n9];
        for (int i = 0; i < n9; ++i) {
            alignmentGenerator = arrobject[i][0];
            Procedure procedure2 = object8[i];
            object5[i] = new Procedure_Int((int)alignmentGenerator, procedure2){
                int index;
                final /* synthetic */ int val$start;
                final /* synthetic */ Procedure val$passThrough;
                {
                    this.val$start = n;
                    this.val$passThrough = procedure;
                    this.index = this.val$start;
                }

                public void pro(int n) {
                    if (n >= this.index) {
                        ArrayList<int[]> arrayList = new ArrayList<int[]>(n - this.index + 1);
                        while (this.index <= n) {
                            arrayList.add(new int[]{this.index++});
                        }
                        this.val$passThrough.pro(arrayList);
                    }
                }
            };
        }
        Procedure_Int_2Args[][] arrprocedure_Int_2Args = new Procedure_Int_2Args[n9][n9];
        for (alignmentGenerator = (ForwardBackwardMatrixIter.AlignmentGenerator)false; alignmentGenerator < n9; ++alignmentGenerator) {
            for (reference var67_115 = alignmentGenerator + true; var67_115 < n9; ++var67_115) {
                arrprocedure_Int_2Args[alignmentGenerator][var67_115] = Librarian.updateMaxOffsets((int)alignmentGenerator, (int)var67_115, arrscrollingQueue_Int2, arrprocedure_Int2, (Librarian.WeightsGetter)object10, arrn4, n9);
                arrprocedure_Int_2Args[var67_115][alignmentGenerator] = Librarian.updateMaxOffsets((int)var67_115, (int)alignmentGenerator, arrscrollingQueue_Int2, arrprocedure_Int2, (Librarian.WeightsGetter)object10, arrn4, n9);
            }
        }
        Librarian.coordinateAlignment(arrfunction, (Generator)object162, object5, arrpairsHeap2, n9, (IntStack)object17, this.diagonalGapBetweenRelations, arrprocedure_Int_2Args);
        if (!floatStack.empty()) {
            throw new IllegalStateException();
        }
        if (!((IntStack)object17).empty()) {
            throw new IllegalStateException();
        }
        if (!((FloatStack)object6).empty()) {
            throw new IllegalStateException();
        }
        if (PairsHeap.used() != 0) {
            throw new IllegalStateException(PairsHeap.used() + " used, total capacity : " + PairsHeap.size());
        }
        for (alignmentGenerator = (ForwardBackwardMatrixIter.AlignmentGenerator)false; alignmentGenerator < n9; ++alignmentGenerator) {
            for (int i = 0; i < n9; ++i) {
                if (alignmentGenerator == i) continue;
                object4 = arrscrollingQueue_Int2[alignmentGenerator][i];
                if (((ScrollingQueue_Int)object4).firstIndex() < arrobject[alignmentGenerator][1] - true) {
                    throw new IllegalStateException(" " + (int)alignmentGenerator + " " + i + " " + ((ScrollingQueue_Int)object4).firstIndex() + " " + (int)arrobject[alignmentGenerator][1] + " ");
                }
                object16 = arrpairsHeap2[alignmentGenerator][i];
                if (object16.firstIndex() >= arrobject[alignmentGenerator][1] - true) continue;
                throw new IllegalStateException(" " + (int)alignmentGenerator + " " + i + " " + object16.firstIndex() + " " + (int)arrobject[alignmentGenerator][1] + " ");
            }
            if (arrpairsHeap2[alignmentGenerator][alignmentGenerator] == null) continue;
            throw new IllegalStateException();
        }
        for (alignmentGenerator = (ForwardBackwardMatrixIter.AlignmentGenerator)false; alignmentGenerator < n9; ++alignmentGenerator) {
            for (int i = 0; i < n9; ++i) {
                if (alignmentGenerator == i || arrn[alignmentGenerator][i][1].gen() == null) continue;
                throw new IllegalStateException(" 1 " + (int)alignmentGenerator + " " + i);
            }
        }
        PairsHeap.report();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.outputFile));
        for (int i = 0; i < arrfastaOutput_Procedure_Int.length; ++i) {
            arrfastaOutput_Procedure_Int[i].endAndClose();
            object4 = new BufferedInputStream(new FileInputStream(arrfile[i]));
            object16 = new FastaParser_Generator_Int((InputStream)object4, Integer.MAX_VALUE);
            object = new FastaOutput_Procedure_Int(bufferedOutputStream, object16.getFastaID());
            while ((n2 = object16.gen()) != Integer.MAX_VALUE) {
                object.pro(n2);
            }
            object.end();
            ((InputStream)object4).close();
            arrfile[i].delete();
        }
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            ((Procedure_NoArgs)iterator.next()).pro();
        }
        if (this.outputAlignmentToGapProbabilitiesToFile) {
            ((PrintWriter)object11).close();
        }
        if (this.outputTopNAlignmentPairsToFile) {
            PrintWriter printWriter = PecanTools.getPrintWriter(this.alignmentPairProbabilitiesFile);
            printWriter.write("Pairs_rejected: " + object2.get(0) + "\n");
            while (((PriorityQueue)object18).size() > 0) {
                object4 = (PairValue)((AbstractQueue)object18).remove();
                printWriter.write(((PairValue)object4).seq1 + " " + (((PairValue)object4).pos1 - arrobject[((PairValue)object4).seq1][0]) + " " + ((PairValue)object4).seq2 + " " + (((PairValue)object4).pos2 - arrobject[((PairValue)object4).seq2][0]) + " " + ((PairValue)object4).weight + "\n");
            }
            printWriter.close();
        }
        logger.info("Total time taken for alignment " + (double)(System.currentTimeMillis() - l) / 1000.0);
    }

    public void setCommandLineArguments(InputMunger inputMunger) throws Exception {
        inputMunger.noInputString("Pecan [options and parameters] ");
        inputMunger.helpString("Pecan [MODIFIER ARGUMENTS]:\nThis is an anchored, consistency based multiple alignment \nprogram written by Benedict Paten. Mail to bjp (AT) ebi.ac.uk");
        inputMunger.addWatch(HMM, 1, "Specify hmm file, default : " + this.hmm);
        inputMunger.addWatch(TRANSITIVE_ANCHORS, 0, "Transitive anchors, (flip) default : " + this.transitiveAnchors);
        inputMunger.addWatch(TRANSITIVE_ANCHOR_DIAGONAL_BOUNDARY, 1, "Width in diagonals surrounding transitive anchors, default : " + this.transitiveAnchorDiagonalBoundary);
        inputMunger.addWatch(ANCHOR_DIAGONAL_WIDTH, 1, "Width in diagonals surrounding standard anchors, default : " + this.anchorDiagonalWidth);
        inputMunger.addWatch(MINIMUM_SIZE_OF_DIAGONAL_FOR_CUT_POINT, 1, "Size of diagonal sufficient to generate a potential cut point, default : " + this.minimumSizeOfDiagonalForCutPoint);
        inputMunger.addWatch(MINIMUM_DIAGONAL_GAP_BETWEEN_CUT_POINT_AND_POLYGON, 1, "The minumum number of diagonal coordinates (x+y) between a cut point and the computed polygon, default : " + this.minimumDiagonalGapBetweenCutPointAndPolygon);
        inputMunger.addWatch(MINIMUM_GAP_BETWEEN_CUT_POINTS, 1, "The minumum number of diagonal coordinates (x+y) between a cut point and the next one, default : " + this.minimumGapBetweenCutPoints);
        inputMunger.addWatch(CONSISTENCY_TRANSFORMATION, 0, "Consistency transformation, default : " + this.consistencyTransformation);
        inputMunger.addWatch(COMPUTATION_THRESHOLD, 1, "Threshold for weights to be used in calculations, default : " + this.computationThreshold);
        inputMunger.addWatch(USE_DIRECT_BYTE_BUFFERS, 0, "Use direct byte buffers (flip), default : " + this.useDirectByteBuffers);
        inputMunger.addWatch(MINIMUM_WEIGHT_HEAP_CAPACITY, 1, "Set a minimum capacity for the weight heap (bytes), default : " + this.minimumWeightHeapCapacity);
        inputMunger.addWatch(PRE_CLOSE_GAPS_LARGER_THAN, 1, "Pre close gaps larger than this length, default : " + this.preCloseGapsLargerThan);
        inputMunger.addWatch(OVERHANGING_DIAGONALS_INTO_PRE_CLOSED_GAPS, 1, "Size of overhanging border (per sequence) into pre-closed gaps, default : " + this.overhangingDiagonalsIntoPreClosedGaps);
        inputMunger.addWatch(REORDER_BY_OUTGROUP_DISTANCE, 1, "Outgroup reordering diagonals distance (per sequence, internal parameter), default : " + this.reorderByOutgroupDistance);
        inputMunger.addWatch(USE_HMM_WITH_JUNK_STATE, 0, "Use HMM with junk state, default : " + this.useHMMWithJunkState);
        inputMunger.addWatch(PROVIDE_CONFIDENCE_VALUES, 0, "Output confidence values, default : " + this.provideConfidenceValues);
        inputMunger.addWatch(INCLUDE_NOT_ALIGNED_VALUES, 0, "Include not aligned probabilities in confidence value, default : " + this.includeNotAlignedValues);
        inputMunger.addWatch(WRITE_CHARACTER_CONFIDENCE_VALUES_IN_MFA, 0, "Include formated confidence values in MFA file, default : " + this.writeCharacterConfidenceValuesInMFA);
        inputMunger.addWatch(WRITE_OUT_SEPERATE_CONFIDENCE_VALUES_FILE, 0, "Write out a seperate confidence values file, default : " + this.writeOutSeperateConfidenceValuesFile);
        inputMunger.addWatch(CONFIDENCE_FILE, 1, "File to write out confidence values, default : " + this.confidenceFile);
        inputMunger.addWatch(THRESHOLD_WEIGHTS_ACCORDING_TO_GAP_PROBABILITY, 0, "Threshold weights according to the probability of a position being aligned to a  gap, like the AMAP program (doesn't work with consistency -- must turn consistency off), default : " + this.thresholdWeightsAccordingToGapProbability);
        inputMunger.addWatch(GAP_GAMMA, 1, "Multiple by which gap alignment probability is judged (increase to infer more gaps, decrease to infer less), default : " + this.gapGamma);
        inputMunger.addWatch(LOAD_CONSTRAINING_ALIGNMENT_FROM_FILE, 0, "Load constraining alignment from file (the final alignment will include all pairs in this alignment), default : " + this.loadConstrainingAlignmentFromFile);
        inputMunger.addWatch(CONSTRAINING_ALIGNMENT_FILE_NAME, 1, "Name of the constraining input alignment, default : " + this.constrainingAlignmentFileName);
        inputMunger.addWatch(OUTPUT_ALIGNMENT_TO_GAP_PROBABILITIES_TO_FILE, 0, "Output the alignment-to-gap probabilities for each residue in each sequence in the alignment, default : " + this.outputAlignmentToGapProbabilitiesToFile);
        inputMunger.addWatch(GAP_PROBABILITIES_FILE, 1, "Alignment-to-gap probabilities file, default : " + this.gapProbabilitiesFile);
        inputMunger.addWatch(OUTPUT_TOP_N_ALIGNMENT_PAIRS_TO_FILE, 0, "Output top N alignment posterior probas to file (if you unwant modified probs turn off the consistency transform), default : " + this.outputTopNAlignmentPairsToFile);
        inputMunger.addWatch(MAXIMUM_NUMBER_OF_ALIGNMENT_PAIRS_TO_WRITE_TO_FILE, 1, "The maximum number of posterior probs to write to file, default : " + this.maximumNumberOfAlignmentPairsToWriteToFile);
        inputMunger.addWatch(ALIGNMENT_PAIR_PROBABILITIES_FILE, 1, "File in which to write alignment pair probs, default : " + this.alignmentPairProbabilitiesFile);
    }

    public void parseArguments(InputMunger inputMunger) {
        this.hmm = inputMunger.parseValue(this.hmm, HMM);
        this.anchorDiagonalWidth = inputMunger.parseValue(this.anchorDiagonalWidth, ANCHOR_DIAGONAL_WIDTH);
        this.minimumSizeOfDiagonalForCutPoint = inputMunger.parseValue(this.minimumSizeOfDiagonalForCutPoint, MINIMUM_SIZE_OF_DIAGONAL_FOR_CUT_POINT);
        this.minimumGapBetweenCutPoints = inputMunger.parseValue(this.minimumGapBetweenCutPoints, MINIMUM_GAP_BETWEEN_CUT_POINTS);
        this.minimumDiagonalGapBetweenCutPointAndPolygon = inputMunger.parseValue(this.minimumDiagonalGapBetweenCutPointAndPolygon, MINIMUM_DIAGONAL_GAP_BETWEEN_CUT_POINT_AND_POLYGON);
        this.computationThreshold = (float)inputMunger.parseValue(this.computationThreshold, COMPUTATION_THRESHOLD);
        if (inputMunger.watchSet(CONSISTENCY_TRANSFORMATION)) {
            this.consistencyTransformation = !this.consistencyTransformation;
        }
        this.transitiveAnchorDiagonalBoundary = inputMunger.parseValue(this.transitiveAnchorDiagonalBoundary, TRANSITIVE_ANCHOR_DIAGONAL_BOUNDARY);
        if (inputMunger.watchSet(TRANSITIVE_ANCHORS)) {
            boolean bl = this.transitiveAnchors = !this.transitiveAnchors;
        }
        if (inputMunger.watchSet(USE_DIRECT_BYTE_BUFFERS)) {
            this.useDirectByteBuffers = !this.useDirectByteBuffers;
        }
        this.minimumWeightHeapCapacity = inputMunger.parseValue(this.minimumWeightHeapCapacity, MINIMUM_WEIGHT_HEAP_CAPACITY);
        this.preCloseGapsLargerThan = inputMunger.parseValue(this.preCloseGapsLargerThan, PRE_CLOSE_GAPS_LARGER_THAN);
        this.overhangingDiagonalsIntoPreClosedGaps = inputMunger.parseValue(this.overhangingDiagonalsIntoPreClosedGaps, OVERHANGING_DIAGONALS_INTO_PRE_CLOSED_GAPS);
        this.reorderByOutgroupDistance = inputMunger.parseValue(this.reorderByOutgroupDistance, REORDER_BY_OUTGROUP_DISTANCE);
        if (inputMunger.watchSet(USE_HMM_WITH_JUNK_STATE)) {
            this.useHMMWithJunkState = !this.useHMMWithJunkState;
        }
        this.outputFile = inputMunger.parseValue(this.outputFile, "OUTPUT_FILE");
        if (inputMunger.watchSet(PROVIDE_CONFIDENCE_VALUES)) {
            boolean bl = this.provideConfidenceValues = !this.provideConfidenceValues;
        }
        if (inputMunger.watchSet(INCLUDE_NOT_ALIGNED_VALUES)) {
            boolean bl = this.includeNotAlignedValues = !this.includeNotAlignedValues;
        }
        if (inputMunger.watchSet(WRITE_CHARACTER_CONFIDENCE_VALUES_IN_MFA)) {
            boolean bl = this.writeCharacterConfidenceValuesInMFA = !this.writeCharacterConfidenceValuesInMFA;
        }
        if (inputMunger.watchSet(WRITE_OUT_SEPERATE_CONFIDENCE_VALUES_FILE)) {
            this.writeOutSeperateConfidenceValuesFile = !this.writeOutSeperateConfidenceValuesFile;
        }
        this.confidenceFile = inputMunger.parseValue(this.confidenceFile, CONFIDENCE_FILE);
        if (inputMunger.watchSet(THRESHOLD_WEIGHTS_ACCORDING_TO_GAP_PROBABILITY)) {
            this.thresholdWeightsAccordingToGapProbability = !this.thresholdWeightsAccordingToGapProbability;
        }
        this.gapGamma = (float)inputMunger.parseValue(this.gapGamma, GAP_GAMMA);
        if (inputMunger.watchSet(LOAD_CONSTRAINING_ALIGNMENT_FROM_FILE)) {
            this.loadConstrainingAlignmentFromFile = !this.loadConstrainingAlignmentFromFile;
        }
        this.constrainingAlignmentFileName = inputMunger.parseValue(this.constrainingAlignmentFileName, CONSTRAINING_ALIGNMENT_FILE_NAME);
        if (inputMunger.watchSet(OUTPUT_ALIGNMENT_TO_GAP_PROBABILITIES_TO_FILE)) {
            this.outputAlignmentToGapProbabilitiesToFile = !this.outputAlignmentToGapProbabilitiesToFile;
        }
        this.gapProbabilitiesFile = inputMunger.parseValue(this.gapProbabilitiesFile, GAP_PROBABILITIES_FILE);
        if (inputMunger.watchSet(OUTPUT_TOP_N_ALIGNMENT_PAIRS_TO_FILE)) {
            this.outputTopNAlignmentPairsToFile = !this.outputTopNAlignmentPairsToFile;
        }
        this.maximumNumberOfAlignmentPairsToWriteToFile = inputMunger.parseValue(this.maximumNumberOfAlignmentPairsToWriteToFile, MAXIMUM_NUMBER_OF_ALIGNMENT_PAIRS_TO_WRITE_TO_FILE);
        this.alignmentPairProbabilitiesFile = inputMunger.parseValue(this.alignmentPairProbabilitiesFile, ALIGNMENT_PAIR_PROBABILITIES_FILE);
    }

    public static void main(String[] arrstring) throws Exception {
        Pecan pecan = new Pecan();
        PrePecan prePecan = new PrePecan();
        InputMunger inputMunger = new InputMunger();
        inputMunger.addStandardWatches();
        prePecan.setCommandLineArguments(inputMunger);
        pecan.setCommandLineArguments(inputMunger);
        if (!inputMunger.parseInput(arrstring)) {
            System.exit(0);
        }
        inputMunger.processStandardWatches();
        prePecan.parseArguments(inputMunger);
        pecan.parseArguments(inputMunger);
        logger.info("Arguments received : " + IterationTools.join(arrstring, " "));
        String[] arrstring2 = inputMunger.watchStrings("SEQUENCES");
        int n = arrstring2.length;
        String string = inputMunger.watchSet("TREE") ? inputMunger.watchStrings("TREE")[0] : PrePecan.makeStarTree(n);
        StringReader stringReader = new StringReader(string);
        NewickTreeParser.Node node = new NewickTreeParser((Generator)NewickTreeParser.commentEater((Generator)NewickTreeParser.tokenise((Reader)stringReader))).tree;
        ((Reader)stringReader).close();
        pecan.PECANScript(node, arrstring2, prePecan);
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.LockedObject;
import bp.common.ds.ScrollingQueue_Int;
import bp.common.ds.wrappers.MutableInteger;
import bp.common.fp.Function;
import bp.common.fp.Function_2Args;
import bp.common.fp.Function_Index_2Args;
import bp.common.fp.Function_Int_4Args;
import bp.common.fp.Function_Int_5Args;
import bp.common.fp.Generator;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_2Args;
import bp.common.fp.Procedure_Int;
import bp.common.fp.Procedure_NoArgs;
import bp.common.io.FastaOutput_Procedure_Int;
import bp.common.io.FastaParser_Generator_Int;
import bp.common.io.NewickTreeParser;
import bp.pecan.AlignmentStitcher;
import bp.pecan.AnchorParser_Generator;
import bp.pecan.PairValue;
import bp.pecan.PairsHeap;
import bp.pecan.Pecan;
import bp.pecan.PolygonFiller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.Logger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class PecanTools {
    static final Logger logger = Logger.getLogger(Pecan.class.getName());

    public static Generator leafGenerator(final NewickTreeParser.Node node) {
        return new Generator(){
            Stack<Object> s = new Stack();
            {
                this.s.push(node);
                this.s.push(node.getNodes().iterator());
            }

            public final Object gen() {
                if (!this.s.empty()) {
                    Iterator iterator = (Iterator)this.s.pop();
                    if (iterator.hasNext()) {
                        this.s.push(iterator);
                        NewickTreeParser.Node node2 = (NewickTreeParser.Node)iterator.next();
                        this.s.push(node2);
                        this.s.push(node2.getNodes().iterator());
                    } else {
                        NewickTreeParser.Node node3 = (NewickTreeParser.Node)this.s.pop();
                        if (node3.getNodes().size() == 0) {
                            return node3;
                        }
                    }
                    return this.gen();
                }
                return null;
            }
        };
    }

    public static int[][] getPairOrdering(double[][] arrd) {
        int n;
        int[][] arrarrn = new int[(arrd.length * arrd.length - arrd.length) / 2][];
        TreeMap treeMap = new TreeMap();
        for (n = 0; n < arrd.length; ++n) {
            for (int i = n + 1; i < arrd.length; ++i) {
                List<int[]> list;
                Double d = new Double(arrd[n][i]);
                int[] arrn = new int[]{n, i};
                if (treeMap.containsKey(d)) {
                    list = (List)treeMap.get(d);
                    list.add(arrn);
                    continue;
                }
                list = new LinkedList();
                list.add(arrn);
                treeMap.put(d, list);
            }
        }
        n = 0;
        while (treeMap.size() != 0) {
            List list = (List)treeMap.remove(treeMap.firstKey());
            for (int[] arrarrn[n++] : list) {
            }
        }
        return arrarrn;
    }

    public static int[][][] getOutgroups(LockedObject lockedObject, double[][] arrd, int n) {
        int[][][] arrn = new int[n][n][];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                arrn[i][j] = PecanTools.getOutgroups(lockedObject, arrd[i], arrd[j], n, i, j);
                arrn[j][i] = arrn[i][j];
            }
        }
        return arrn;
    }

    public static int[] getOutgroups(LockedObject lockedObject, double[] arrd, double[] arrd2, int n, int n2, int n3) {
        int n4 = 0;
        double d = arrd[n3];
        int[] arrn = (int[])lockedObject.get();
        for (int i = 0; i < n; ++i) {
            if (i == n2 || i == n3 || !(arrd[i] < d) && (arrd[i] != d || i >= n3) || !(arrd2[i] < d) && (arrd2[i] != d || i >= n2)) continue;
            arrn[n4++] = i;
        }
        int[] arrn2 = new int[n4];
        System.arraycopy(arrn, 0, arrn2, 0, n4);
        lockedObject.release();
        return arrn2;
    }

    public static void replaceEdgeLengths(NewickTreeParser.Node node, double d, double d2) {
        Iterator<Object> iterator = node.getNodes().iterator();
        while (iterator.hasNext()) {
            PecanTools.replaceEdgeLengths((NewickTreeParser.Node)iterator.next(), d, d2);
        }
        if (node.edgeLength == d) {
            node.edgeLength = d2;
        }
    }

    public static double[][] getDistances(NewickTreeParser.Node node, int n, Function_2Args function_2Args) {
        double[][] arrd = new double[n][n];
        PecanTools.getDistances(node, arrd, 0, function_2Args);
        return arrd;
    }

    private static double[] getDistances(NewickTreeParser.Node node, double[][] arrd, int n, Function_2Args function_2Args) {
        if (node.getNodes().size() != 0) {
            Object object;
            List<Object> list = node.getNodes();
            LinkedList<Object> linkedList = new LinkedList<Object>();
            int n2 = n;
            double[] arrd2 = list.iterator();
            while (arrd2.hasNext()) {
                object = PecanTools.getDistances((NewickTreeParser.Node)arrd2.next(), arrd, n2, function_2Args);
                int n3 = n;
                for (double[] arrd3 : linkedList) {
                    for (int i = 0; i < ((Object)object).length; ++i) {
                        for (int j = 0; j < arrd3.length; ++j) {
                            double d;
                            arrd[n2 + i][n3 + j] = d = ((Number)function_2Args.fn(new Double((double)object[i]), new Double(arrd3[j]))).doubleValue();
                            arrd[n3 + j][n2 + i] = d;
                        }
                    }
                    n3 += arrd3.length;
                }
                linkedList.add(object);
                n2 += ((Object)object).length;
            }
            arrd2 = new double[n2 - n];
            n2 = 0;
            for (double[] arrd4 : linkedList) {
                System.arraycopy(arrd4, 0, arrd2, n2, arrd4.length);
                n2 += arrd4.length;
            }
            object = new Double(node.edgeLength);
            for (int i = 0; i < arrd2.length; ++i) {
                arrd2[i] = ((Number)function_2Args.fn(new Double(arrd2[i]), object)).doubleValue();
            }
            return arrd2;
        }
        arrd[n][n] = Double.NaN;
        return new double[]{node.edgeLength};
    }

    public static Function_Index_2Args transitiveAnchors(final int n, final ScrollingQueue_Int scrollingQueue_Int) {
        return new Function_Index_2Args(){
            int pI;
            int pJ;
            {
                this.pI = scrollingQueue_Int.firstIndex();
                this.pJ = Integer.MIN_VALUE;
            }

            public final Object fn(int n4, int n2) {
                if (this.pI < n4 + n) {
                    this.pI = n4 + n;
                }
                while (this.pI < n2 - n && this.pI < scrollingQueue_Int.lastIndex()) {
                    int n3;
                    if (this.pI >= scrollingQueue_Int.firstIndex() && (n3 = scrollingQueue_Int.get(this.pI)) != Integer.MAX_VALUE && n3 > this.pJ) {
                        this.pJ = n3;
                        PolygonFiller.Node node = new PolygonFiller.Node(this.pJ + n, this.pI - n, this.pI - n, 1);
                        ++this.pI;
                        return node;
                    }
                    ++this.pI;
                }
                return null;
            }
        };
    }

    public static int toFile(Generator generator, String string, boolean bl) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string, bl));
            int n = AnchorParser_Generator.writeOutEdgeList(generator, bufferedOutputStream);
            ((OutputStream)bufferedOutputStream).close();
            return n;
        }
        catch (IOException iOException) {
            throw new IllegalStateException();
        }
    }

    public static String getTempFileHandle() {
        File file;
        try {
            file = File.createTempFile("edgeList", null);
            file.deleteOnExit();
        }
        catch (IOException iOException) {
            throw new IllegalStateException();
        }
        return file.getAbsolutePath();
    }

    public static NewickTreeParser.Node getCommonAncestor(NewickTreeParser.Node node, NewickTreeParser.Node node2) {
        ArrayList<NewickTreeParser.Node> arrayList = new ArrayList<NewickTreeParser.Node>();
        while (node != null) {
            arrayList.add(node);
            node = node.getParent();
        }
        while (node2 != null) {
            if (arrayList.contains(node2)) {
                return node2;
            }
            node2 = node2.getParent();
        }
        return null;
    }

    public static double averagePathLengthToChildren(NewickTreeParser.Node node) {
        MutableInteger mutableInteger = new MutableInteger(0);
        double d = PecanTools.totalPathLengthToChildren(node, mutableInteger);
        return d / (double)mutableInteger.i;
    }

    private static double totalPathLengthToChildren(NewickTreeParser.Node node, MutableInteger mutableInteger) {
        if (node.getNodes().size() == 0) {
            ++mutableInteger.i;
            return node.edgeLength;
        }
        double d = 0.0;
        int n = mutableInteger.i;
        Iterator<Object> iterator = node.getNodes().iterator();
        while (iterator.hasNext()) {
            d += PecanTools.totalPathLengthToChildren((NewickTreeParser.Node)iterator.next(), mutableInteger);
        }
        return (double)(mutableInteger.i - n) * node.edgeLength + d;
    }

    public static Function_Int_4Args sumTotal(final PairsHeap[][] arrpairsHeap) {
        return new Function_Int_4Args(){

            public int fn(int n, int n2, int n3, int n4) {
                return arrpairsHeap[n][n2].sumTotal(n3, n4);
            }
        };
    }

    public static Function_Int_5Args getWeight(final PairsHeap[][] arrpairsHeap) {
        return new Function_Int_5Args(){

            public int fn(int n, int n2, int n3, int n4, int n5) {
                return arrpairsHeap[n][n2].getWeight(n3, n4, n5);
            }
        };
    }

    public static Function totalScore(final Function_Int_5Args function_Int_5Args) {
        return new Function(){

            public Object fn(Object object) {
                return new Float(PecanTools.totalScore((int[])object, function_Int_5Args));
            }
        };
    }

    static float totalScore(int[] arrn, Function_Int_5Args function_Int_5Args) {
        float f = 0.0f;
        int n = 0;
        int n2 = Float.floatToRawIntBits(0.0f);
        for (int i = 0; i < arrn.length; ++i) {
            int n3 = arrn[i];
            if (n3 == Integer.MAX_VALUE) continue;
            ++n;
            for (int j = i + 1; j < arrn.length; ++j) {
                int n4 = arrn[j];
                if (n4 == Integer.MAX_VALUE) continue;
                float f2 = Float.intBitsToFloat(function_Int_5Args.fn(i, j, n3, n4, n2));
                f += f2;
            }
        }
        if (n != 1) {
            f /= (float)((n * n - 1) / 2);
        }
        return f;
    }

    public static Function totalScoreMinus(final Function_Int_5Args function_Int_5Args, final Function_Int_4Args function_Int_4Args) {
        return new Function(){

            public Object fn(Object object) {
                return new Float(PecanTools.totalScore_Minus((int[])object, function_Int_5Args, function_Int_4Args));
            }
        };
    }

    static float totalScore_Minus(int[] arrn, Function_Int_5Args function_Int_5Args, Function_Int_4Args function_Int_4Args) {
        float f = 0.0f;
        int n = 0;
        int n2 = Float.floatToRawIntBits(0.0f);
        for (int i = 0; i < arrn.length; ++i) {
            int n3;
            int n4;
            float f2;
            int n5 = arrn[i];
            if (n5 == Integer.MAX_VALUE) continue;
            ++n;
            for (n4 = 0; n4 < i; ++n4) {
                n3 = arrn[n4];
                if (n3 != Integer.MAX_VALUE) continue;
                f2 = 1.0f - Float.intBitsToFloat(function_Int_4Args.fn(i, n4, n5, n2));
                f += f2;
            }
            for (n4 = i + 1; n4 < arrn.length; ++n4) {
                n3 = arrn[n4];
                if (n3 != Integer.MAX_VALUE) {
                    f2 = Float.intBitsToFloat(function_Int_5Args.fn(i, n4, n5, n3, n2));
                    f += f2;
                    continue;
                }
                f2 = 1.0f - Float.intBitsToFloat(function_Int_4Args.fn(i, n4, n5, n2));
                f += f2;
            }
        }
        return f /= (float)((n * n - 1) / 2 + (arrn.length - n) * n);
    }

    public static void outputTrack(List<Procedure_Int> list, List<Procedure_NoArgs> list2, final String string, final String string2) throws IOException {
        final File file = File.createTempFile("tmp_output_", ".fa");
        file.deleteOnExit();
        final FastaOutput_Procedure_Int fastaOutput_Procedure_Int = new FastaOutput_Procedure_Int(new BufferedOutputStream(new FileOutputStream(file)), "pecan confidence values");
        list.add(new Procedure_Int(){
            char[] cA = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '*'};

            public void pro(int n) {
                float f = Float.intBitsToFloat(n);
                int n2 = Math.round(f * 10.0f);
                fastaOutput_Procedure_Int.pro(this.cA[n2]);
            }
        });
        list2.add(new Procedure_NoArgs(){

            public void pro() {
                try {
                    fastaOutput_Procedure_Int.endAndClose();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string, true));
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    FastaParser_Generator_Int fastaParser_Generator_Int = new FastaParser_Generator_Int(bufferedInputStream, Integer.MAX_VALUE);
                    FastaOutput_Procedure_Int.writeFile(bufferedOutputStream, string2, fastaParser_Generator_Int, Integer.MAX_VALUE);
                    ((InputStream)bufferedInputStream).close();
                    ((OutputStream)bufferedOutputStream).close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    throw new IllegalStateException();
                }
            }
        });
    }

    public static void outputFloatFile(List<Procedure_Int> list, List<Procedure_NoArgs> list2, String string) throws IOException {
        final PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(string)));
        list.add(new Procedure_Int(){

            public void pro(int n) {
                float f = Float.intBitsToFloat(n);
                printWriter.println(f);
            }
        });
        list2.add(new Procedure_NoArgs(){

            public void pro() {
                printWriter.close();
            }
        });
    }

    public static Procedure outputTrackAdaptor(final Function function, final int n, final List<Procedure_Int> list, final Procedure procedure) {
        return new Procedure(){
            Procedure_2Args convertPositions = AlignmentStitcher.convertInput();
            int[] iA = new int[n];

            public void pro(Object object) {
                for (Object e : (List)object) {
                    this.convertPositions.pro(e, this.iA);
                    float f = ((Float)function.fn(this.iA)).floatValue();
                    int n2 = Float.floatToRawIntBits(f);
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        ((Procedure_Int)iterator.next()).pro(n2);
                    }
                }
                procedure.pro(object);
            }
        };
    }

    public static PrintWriter getPrintWriter(String string) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(string)));
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            throw new IllegalStateException();
        }
        return printWriter;
    }

    public static Procedure outputAlignmentPairs(final PairsHeap[][] arrpairsHeap, final int n, final Procedure procedure, final PriorityQueue<PairValue> priorityQueue, final List<Integer> list, final int n2) {
        return new Procedure(){
            Procedure_2Args convertPositions = AlignmentStitcher.convertInput();
            int[] iA = new int[n];
            int[] iA2 = new int[1000];
            int numberRejected = 0;

            public void pro(Object object) {
                for (Object e : (List)object) {
                    this.convertPositions.pro(e, this.iA);
                    for (int i = 0; i < this.iA.length; ++i) {
                        if (this.iA[i] == Integer.MAX_VALUE) continue;
                        for (int j = i + 1; j < n; ++j) {
                            int n3 = arrpairsHeap[i][j].get(this.iA[i], 0, this.iA2);
                            for (int k = 0; k < n3; k += 2) {
                                PairValue pairValue;
                                float f = Float.intBitsToFloat(this.iA2[k + 1]);
                                if (priorityQueue.size() >= n2) {
                                    pairValue = (PairValue)priorityQueue.peek();
                                    if (!(pairValue.weight < f)) continue;
                                    list.set(0, new Integer(this.numberRejected++));
                                    priorityQueue.poll();
                                    pairValue = new PairValue(i, j, this.iA[i], this.iA2[k], f);
                                    priorityQueue.add(pairValue);
                                    continue;
                                }
                                pairValue = new PairValue(i, j, this.iA[i], this.iA2[k], f);
                                priorityQueue.add(pairValue);
                            }
                        }
                    }
                }
                procedure.pro(object);
            }
        };
    }

    public static Procedure outputGapAlignments(final PairsHeap[][] arrpairsHeap, final int n, final Procedure procedure, final PrintWriter printWriter) {
        return new Procedure(){
            Procedure_2Args convertPositions = AlignmentStitcher.convertInput();
            int[] iA = new int[n];
            int[] iA2 = new int[1000];
            int[] seqs = new int[n];

            public void pro(Object object) {
                for (Object e : (List)object) {
                    this.convertPositions.pro(e, this.iA);
                    for (int i = 0; i < this.iA.length; ++i) {
                        if (this.iA[i] == Integer.MAX_VALUE) continue;
                        for (int j = 0; j < n; ++j) {
                            if (i == j) continue;
                            float f = 0.0f;
                            int n2 = arrpairsHeap[i][j].get(this.iA[i], 0, this.iA2);
                            for (int k = 0; k < n2; k += 2) {
                                f += Float.intBitsToFloat(this.iA2[k + 1]);
                            }
                            printWriter.write(i + " " + this.seqs[i] + " " + j + " " + (1.0f - f) + "\n");
                        }
                        int n3 = i;
                        this.seqs[n3] = this.seqs[n3] + 1;
                    }
                }
                procedure.pro(object);
            }
        };
    }
    static class CutUpDiagonals
    implements Generator,
    Procedure_Int {
        int x;
        int y = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        final Generator gen;
        int cutDiagonal = Integer.MIN_VALUE;

        CutUpDiagonals(Generator generator) {
            this.gen = generator;
        }

        public final void setCutUpPoint(int n) {
            this.cutDiagonal = n;
        }

        public final void pro(int n) {
            this.setCutUpPoint(n);
        }

        public final Object gen() {
            if (this.y <= this.yMax) {
                if (this.y + this.x > this.cutDiagonal) {
                    return new PolygonFiller.Node(this.x++, this.y, this.y++, 1);
                }
                if (2 * this.yMax + this.x - this.y > this.cutDiagonal) {
                    int n = (this.cutDiagonal - this.x - this.y) / 2;
                    PolygonFiller.Node node = new PolygonFiller.Node(this.x, this.y, this.y + n, 1);
                    this.x += n + 1;
                    this.y += n + 1;
                    return node;
                }
                PolygonFiller.Node node = new PolygonFiller.Node(this.x, this.y, this.yMax, 1);
                this.y = this.yMax + 1;
                return node;
            }
            PolygonFiller.Node node = (PolygonFiller.Node)this.gen.gen();
            if (node == null) {
                return null;
            }
            this.y = node.y;
            this.x = node.x;
            if (2 * node.yMax + this.x - this.y > this.cutDiagonal) {
                if (this.cutDiagonal != Integer.MIN_VALUE && this.x + this.y > this.cutDiagonal) {
                    logger.info("An unintended " + this.cutDiagonal + " " + this.x + " " + this.y);
                }
                if (this.x + this.y <= this.cutDiagonal) {
                    int n = (this.cutDiagonal - this.x - this.y) / 2;
                    PolygonFiller.Node node2 = new PolygonFiller.Node(this.x, this.y, this.y + n, 1);
                    this.x += n + 1;
                    this.y += n + 1;
                    this.yMax = node.yMax;
                    return node2;
                }
                this.yMax = node.yMax;
                return this.gen();
            }
            return node;
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.Array;
import bp.common.fp.Function;
import bp.common.fp.Function_Index;
import bp.common.fp.Function_Index_2Args;
import bp.common.fp.Generator;
import bp.common.fp.GeneratorIterator;
import bp.common.fp.Generators;
import bp.common.fp.Iterators;
import bp.common.fp.Predicate;
import bp.common.fp.Procedure_Int_2Args;
import bp.pecan.Pecan;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public final class PolygonFiller {
    static final Logger logger = Logger.getLogger(Pecan.class.getName());

    public static final void scanPolygon(Iterator iterator, Iterator iterator2, Procedure_Int_2Args procedure_Int_2Args) {
        if (iterator.hasNext()) {
            Node node = (Node)iterator.next();
            int n = node.y;
            int n2 = node.x;
            Node node2 = (Node)iterator2.next();
            int n3 = node2.x;
            do {
                procedure_Int_2Args.pro(n2, n3);
                n2 += node.z;
                n3 += node2.z;
                if (n == node.yMax) {
                    if (!iterator.hasNext()) break;
                    node = (Node)iterator.next();
                    n2 = node.x;
                }
                if (n == node2.yMax) {
                    node2 = (Node)iterator2.next();
                    n3 = node2.x;
                }
                ++n;
            } while (true);
        }
    }

    static List reverseCoordinates(List list, int n, int n2) {
        LinkedList<Node> linkedList = new LinkedList<Node>();
        for (Node node : list) {
            int n3 = node.x;
            int n4 = node.y;
            int n5 = node.yMax;
            int n6 = node.z;
            if (n6 != 0) {
                if (n6 == 1) {
                    n3 = n - (n3 + n5 - n4);
                } else {
                    if (n6 != -1) {
                        throw new IllegalStateException();
                    }
                    n3 = n - (n3 - n5 + n4);
                }
            } else {
                n3 = n - n3;
            }
            int n7 = n5;
            n5 = n2 - n4;
            n4 = n2 - n7;
            linkedList.add(0, new Node(n3, n4, n5, n6));
        }
        return linkedList;
    }

    public static void transformEdgeList(List list, int n, int n2) {
        for (Node node : list) {
            node.y += n2;
            node.yMax += n2;
            node.x += n;
        }
    }

    public static Iterator transformEdges(Iterator iterator, int n, int n2) {
        return new GeneratorIterator(PolygonFiller.transformEdges(Generators.iteratorGenerator(iterator), n, n2));
    }

    public static Generator transformEdges(final Generator generator, final int n, final int n2) {
        return new Generator(){

            public Object gen() {
                Node node = (Node)generator.gen();
                if (node != null) {
                    PolygonFiller.transformEdge(n, n2, node);
                    return node;
                }
                return null;
            }
        };
    }

    public static Function transformEdge(final int n, final int n2) {
        return new Function(){

            public Object fn(Object object) {
                PolygonFiller.transformEdge(n, n2, (Node)object);
                return object;
            }
        };
    }

    public static final void transformEdge(int n, int n2, Node node) {
        node.y += n2;
        node.yMax += n2;
        node.x += n;
    }

    public static Iterator combineEdgeLists(Iterator iterator, Iterator iterator2) {
        return Iterators.merge(iterator, iterator2, new Comparator(){

            public int compare(Object object, Object object2) {
                return ((Comparable)object).compareTo(object2);
            }
        });
    }

    public static Generator flipEdgeXYDiagonalsCoordinates(final Generator generator) {
        return new Generator(){

            public Object gen() {
                Node node = (Node)generator.gen();
                if (node != null) {
                    int n = node.y;
                    node.yMax = node.x + node.yMax - n;
                    node.y = node.x;
                    node.x = n;
                    return node;
                }
                return null;
            }
        };
    }

    public static Function_Index clipPolygons(final Generator generator, final Generator generator2, final int n, final int n2) {
        return new Function_Index(){
            Node leftE;
            Node rightE;
            List<Node> lET;
            List<Node> rET;
            int[] boundaries;
            {
                this.leftE = (Node)generator.gen();
                this.rightE = (Node)generator2.gen();
                this.lET = new LinkedList<Node>();
                this.rET = new LinkedList<Node>();
                this.boundaries = new int[8];
                if (this.leftE == null) {
                    this.leftE = new Node(n, n2, n2, 1);
                }
                if (this.rightE == null) {
                    this.rightE = new Node(n, n2, n2, 1);
                }
                this.fillInValues(0, this.boundaries, this.leftE.x, this.leftE.y, this.leftE.x, this.leftE.y);
            }

            @Override
            public final Object fn(int n4) {
                Object object;
                if (this.boundaries[0] + this.boundaries[1] > n4 || n4 > n2 + n - 2) {
                    throw new IllegalStateException(this.boundaries[0] + this.boundaries[1] + " " + n4 + " " + (n2 + n - 2));
                }
                int[] arrn = this.boundaries;
                this.boundaries = new int[8];
                this.leftE = this.getEdgesLessThanDiagonal(this.leftE, generator, n4 + 1, this.lET);
                this.rightE = this.getEdgesLessThanDiagonal(this.rightE, generator2, n4, this.rET);
                if (this.rightE.y != this.leftE.y) {
                    object = this.createDiagonalLine(n4);
                    this.fillInValues(4, arrn, ((Node)object).x, ((Node)object).y, ((Node)object).x - (((Node)object).yMax - ((Node)object).y), ((Node)object).yMax);
                    if (this.rightE.y + this.rightE.x == n4) {
                        this.rightE = this.shiftLineUpOne(this.rightE, generator2);
                    }
                    this.rET.add((Node)object);
                } else {
                    object = this.rET.get(this.rET.size() - 1);
                    int n22 = ((Node)object).x + ((Node)object).z * (((Node)object).yMax - ((Node)object).y);
                    this.fillInValues(4, arrn, n22, ((Node)object).yMax, n22, ((Node)object).yMax);
                }
                object = this.lET;
                List<Node> list = this.rET;
                this.lET = new LinkedList<Node>();
                this.rET = new LinkedList<Node>();
                if (this.rightE.y != this.leftE.y) {
                    Node node = this.createDiagonalLine(n4 + 1);
                    this.lET.add(node);
                    int n3 = node.yMax + (this.leftE.y + this.leftE.x == n4 + 1 ? 1 : 0);
                    this.fillInValues(0, this.boundaries, node.x - (n3 - node.y), n3, node.x, node.y);
                } else {
                    this.fillInValues(0, this.boundaries, this.leftE.x, this.leftE.y, this.leftE.x, this.leftE.y);
                }
                return new Object[]{object, list, arrn};
            }

            void fillInValues(int n6, int[] arrn, int n22, int n3, int n4, int n5) {
                arrn[n6] = n22;
                arrn[n6 + 1] = n3;
                arrn[n6 + 2] = n4;
                arrn[n6 + 3] = n5;
            }

            Node createDiagonalLine(int n3) {
                return new Node(n3 - this.rightE.y, this.rightE.y, this.leftE.y - 1, -1);
            }

            Node shiftLineUpOne(Node node, Generator generator3) {
                if (node.y == node.yMax) {
                    node = (Node)generator3.gen();
                    if (node == null) {
                        node = new Node(n, n2, n2, 1);
                    }
                } else {
                    ++node.y;
                    node.x += node.z;
                }
                return node;
            }

            Node getEdgesLessThanDiagonal(Node node, Generator generator3, int n6, List<Node> list) {
                int n22;
                while ((n22 = node.x + node.z * (node.yMax - node.y) + node.yMax) < n6) {
                    list.add(node);
                    node = (Node)generator3.gen();
                    if (node != null) continue;
                    node = new Node(n, n2, n2, 1);
                }
                int n3 = n6 - node.y - node.x;
                if (n3 > 0) {
                    list.add(node);
                    if (node.z == 1) {
                        int n4 = node.yMax;
                        node.yMax = node.y + (n3 - 1) / 2;
                        node = new Node(node.x + (n3 + 1) / 2, node.y + (n3 + 1) / 2, n4, 1);
                    } else {
                        int n5 = node.yMax;
                        node.yMax = node.y + n3 - 1;
                        node = new Node(node.x, node.y + n3, n5, 0);
                    }
                }
                return node;
            }
        };
    }

    public static Generator addVerticalEdgesLeft(final Generator generator, final int n, final int n2, final int n3) {
        return new Generator(){
            Node cL;
            int x;
            int y;
            int yS;
            {
                this.x = n;
                this.y = n2;
                this.cL = (Node)generator.gen();
                this.yS = this.cL != null ? this.cL.y : n3;
            }

            public Object gen() {
                if (this.y < this.yS) {
                    Node node = new Node(this.x, this.y, this.yS - 1, 0);
                    this.y = Integer.MAX_VALUE;
                    return node;
                }
                if (this.cL != null) {
                    this.y = this.cL.yMax + 1;
                    this.x = this.cL.x + this.cL.yMax - this.cL.y;
                    Node node = this.cL;
                    this.cL = (Node)generator.gen();
                    this.yS = this.cL != null ? this.cL.y : n3;
                    return node;
                }
                return null;
            }
        };
    }

    public static Generator addVerticalEdgesRight(final Generator generator, final int n, final int n2, final int n3) {
        return new Generator(){
            Node cL;
            int y;
            int yS;
            {
                this.y = n2;
                this.cL = (Node)generator.gen();
                this.yS = this.cL != null ? this.cL.y : n2;
            }

            public Object gen() {
                if (this.y < this.yS) {
                    Node node = new Node(this.cL.x - 1, this.y, this.yS - 1, 0);
                    this.y = this.cL.yMax;
                    return node;
                }
                Object object = generator.gen();
                if (object != null) {
                    Node node = this.cL;
                    this.cL = (Node)object;
                    if (this.yS == node.yMax) {
                        this.yS = this.cL.y;
                        return this.gen();
                    }
                    this.yS = this.cL.y;
                    this.y = node.yMax--;
                    return node;
                }
                if (this.cL != null && this.yS != this.cL.yMax) {
                    this.y = this.cL.yMax--;
                    Node node = this.cL;
                    this.cL = null;
                    return node;
                }
                if (this.y < n3) {
                    Node node = new Node(n - 1, this.y, n3 - 1, 0);
                    this.y = Integer.MAX_VALUE;
                    return node;
                }
                return null;
            }
        };
    }

    public static Function_Index polygonIterator(Generator generator, Generator generator2, int n, int n2, int n3, int n4) {
        return PolygonFiller.clipPolygons(PolygonFiller.addVerticalEdgesLeft(PolygonFiller.flipEdgeXYDiagonalsCoordinates(generator2), n - 1, n3 - 1, n4), PolygonFiller.addVerticalEdgesRight(generator, n2, n3 - 1, n4), n2, n4);
    }

    public static Generator cutPointGenerator(final Generator generator, final int n, final int n2) {
        return new Generator(){
            int i;
            int j;
            int k;
            int m;
            int n = Integer.MAX_VALUE;

            public final Object gen() {
                if (this.n == Integer.MAX_VALUE) {
                    Node node = (Node)generator.gen();
                    if (node != null) {
                        this.j = node.y - 1;
                        this.k = node.yMax - this.j;
                        if (this.k >= n) {
                            this.i = node.x - 1;
                            this.n = 2 + this.k / n2;
                            this.m = this.k / this.n;
                            if (this.m == 0) {
                                this.m = 1;
                            }
                            this.k = this.m * (this.n - 1);
                            this.n = this.m;
                            return this.gen();
                        }
                        return this.gen();
                    }
                    return null;
                }
                if (this.n <= this.k) {
                    int[] arrn = new int[]{this.i + this.n, this.j + this.n};
                    this.n += this.m;
                    return arrn;
                }
                this.n = Integer.MAX_VALUE;
                return this.gen();
            }
        };
    }

    public static Generator nERPoints(final Generator generator, final int n, final int n2, final int n3, final int n4, final int n5) {
        return new Generator(){
            int pD;
            int pX;
            {
                this.pD = n2 + n3;
                this.pX = n2;
            }

            public Object gen() {
                Node node;
                while ((node = (Node)generator.gen()) != null) {
                    if (node.x + node.y > this.pD + n) {
                        int[] arrn = new int[]{this.pX, this.pD - this.pX, node.x, node.y};
                        this.pD = 2 * node.yMax + node.x - node.y;
                        this.pX = node.x - node.y + node.yMax;
                        return arrn;
                    }
                    this.pD = 2 * node.yMax + node.x - node.y;
                    this.pX = node.x - node.y + node.yMax;
                }
                if (n4 + n5 > this.pD + n) {
                    int[] arrn = new int[]{this.pX, this.pD - this.pX, n4, n5};
                    this.pD = n4 + n5;
                    this.pX = n4;
                    return arrn;
                }
                return null;
            }
        };
    }

    public static final Predicate filterEdgeListByLessThanConstraints(final Generator generator, final int n) {
        return new Predicate(){
            int lTX;
            int lTY;
            {
                this.getLT();
            }

            public final boolean test(Object object) {
                Node node = (Node)object;
                if (node.y >= this.lTY) {
                    while (node.x > this.lTX) {
                        this.getLT();
                    }
                    return node.y < this.lTY;
                }
                return true;
            }

            final void getLT() {
                int[] arrn = (int[])generator.gen();
                if (arrn != null) {
                    this.lTX = arrn[1] + n;
                    this.lTY = arrn[0] - n;
                } else {
                    this.lTX = Integer.MAX_VALUE;
                    this.lTY = Integer.MAX_VALUE;
                }
            }
        };
    }

    public static final Predicate filterEdgeListByLessThanOrEqualConstraints(final Generator generator, final int n) {
        return new Predicate(){
            int lTX;
            int lTXMax = Integer.MIN_VALUE;
            int lTY = Integer.MIN_VALUE;

            public final boolean test(Object object) {
                Node node = (Node)object;
                if (node.y >= this.lTY) {
                    this.getLT(node.x);
                    return node.y < this.lTY;
                }
                return true;
            }

            final void getLT(int n2) {
                Node node;
                if (n2 <= this.lTXMax) {
                    if (n2 > this.lTX) {
                        this.lTY += n2 - this.lTX;
                        this.lTX = n2;
                    }
                    return;
                }
                while ((node = (Node)generator.gen()) != null) {
                    this.lTXMax = node.yMax + n;
                    if (n2 > this.lTXMax) continue;
                    this.lTX = node.y + n;
                    this.lTY = node.x - n;
                    if (n2 > this.lTX) {
                        this.lTY += n2 - this.lTX;
                        this.lTX = n2;
                    }
                    return;
                }
                this.lTY = Integer.MAX_VALUE;
                this.lTX = Integer.MAX_VALUE;
                this.lTXMax = Integer.MAX_VALUE;
            }
        };
    }

    public static final Generator mergeLessThansEdgeList(final Generator generator, final Generator generator2) {
        return new Generator(){
            Node m = null;
            Node n = (Node)generator2.gen();
            int pY = Integer.MIN_VALUE;

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public final Object gen() {
                if (this.m == null) {
                    this.m = (Node)generator.gen();
                    if (this.m == null) {
                        if (this.n == null) {
                            return null;
                        }
                        v0 = this.returnN();
                        return v0;
                    }
                }
                if (this.n == null) {
                    return this.returnM();
                }
                do lbl-1000:
                // 4 sources
                {
                    block9: {
                        block8: {
                            if (this.m.x >= this.n.x) break block8;
                            if (this.m.y > this.pY) {
                                this.pY = this.m.y;
                                var1_1 = this.m;
                                this.m = null;
                                return var1_1;
                            }
                            this.m = (Node)generator.gen();
                            if (this.m != null) ** GOTO lbl-1000
                            return this.returnN();
                        }
                        if (this.m.x != this.n.x) break block9;
                        if (this.m.y > this.n.y) {
                            this.n = (Node)generator2.gen();
                            this.pY = this.m.y;
                            var1_2 = this.m;
                            this.m = null;
                            return var1_2;
                        }
                        this.m = (Node)generator.gen();
                        if (this.m != null) ** GOTO lbl-1000
                        return this.returnN();
                    }
                    if (this.n.y > this.pY) {
                        this.pY = this.n.y;
                        var1_3 = this.n;
                        this.n = (Node)generator2.gen();
                        return var1_3;
                    }
                    this.n = (Node)generator2.gen();
                } while (this.n != null);
                return this.returnM();
            }

            final Node returnM() {
                do {
                    if (this.m.y > this.pY) {
                        Node node = this.m;
                        this.m = null;
                        return node;
                    }
                    this.m = (Node)generator.gen();
                } while (this.m != null);
                return null;
            }

            final Node returnN() {
                do {
                    if (this.n.y > this.pY) {
                        this.pY = this.n.y;
                        Node node = this.n;
                        this.n = (Node)generator2.gen();
                        return node;
                    }
                    this.n = (Node)generator2.gen();
                } while (this.n != null);
                return null;
            }
        };
    }

    public static final Generator mergeEdgeListWithTransitiveAnchors(final Generator generator, final Function_Index_2Args function_Index_2Args, final Predicate predicate, final Predicate predicate2) {
        return new Generator(){
            int pX = Integer.MIN_VALUE;
            int pY = Integer.MIN_VALUE;
            int x;
            int y;
            Node m;
            Node n;
            {
                this.getM();
            }

            public final Object gen() {
                if (this.n == null) {
                    this.n = (Node)function_Index_2Args.fn(this.pY + 1, this.y);
                    if (this.n == null) {
                        return this.returnM();
                    }
                }
                while (this.n.x <= this.pX || this.n.x >= this.x || !predicate.test(this.n) || !predicate2.test(this.n)) {
                    this.n = (Node)function_Index_2Args.fn(this.pY + 1, this.y);
                    if (this.n != null && this.n.y < this.y) continue;
                    return this.returnM();
                }
                Node node = this.n;
                this.n = (Node)function_Index_2Args.fn(this.pY + 1, this.y);
                return node;
            }

            final Object returnM() {
                if (this.m == null) {
                    return null;
                }
                Node node = this.m;
                this.pX = this.m.x + this.m.yMax - this.m.y;
                this.pY = this.m.yMax;
                this.getM();
                return node;
            }

            final void getM() {
                this.m = (Node)generator.gen();
                if (this.m != null) {
                    this.x = this.m.x;
                    this.y = this.m.y;
                } else {
                    this.x = Integer.MAX_VALUE;
                    this.y = Integer.MAX_VALUE;
                }
            }
        };
    }

    public static List cloneEdgeList(List list) {
        LinkedList<Object> linkedList = new LinkedList<Object>();
        for (Node node : list) {
            linkedList.add(node.clone());
        }
        return linkedList;
    }

    public static Function cloneEdge() {
        return new Function(){

            public Object fn(Object object) {
                return ((Node)object).clone();
            }
        };
    }

    public static Predicate isLessThan() {
        return new Predicate(){

            public boolean test(Object object) {
                return ((Node)object).z == 0;
            }
        };
    }

    public static Predicate isLessThanOrEqual() {
        return new Predicate(){

            public boolean test(Object object) {
                return ((Node)object).z != 0;
            }
        };
    }

    public static Function_Index polygonIteratorWithLessThans(final Function_Index function_Index, final Generator generator, final Generator generator2, final int[] arrn) {
        return new Function_Index(){
            int[] iA1 = null;
            int[] iA2 = null;
            int upto;

            public Object fn(int n) {
                Object[] arrobject = (Object[])function_Index.fn(n);
                this.iA1 = this.lessThanPoints(generator, this.iA1, arrn, n);
                int n2 = this.upto;
                int[] arrn3 = new int[n2];
                System.arraycopy(arrn, 0, arrn3, 0, n2);
                this.iA2 = this.lessThanPoints(generator2, this.iA2, arrn, n);
                n2 = this.upto;
                Array.mingle(arrn, n2 - 2);
                int[] arrn2 = new int[n2];
                System.arraycopy(arrn, 0, arrn2, 0, n2);
                Object[] arrobject2 = new Object[arrobject.length + 2];
                System.arraycopy(arrobject, 0, arrobject2, 0, arrobject.length);
                arrobject2[arrobject2.length - 1] = arrn3;
                arrobject2[arrobject2.length - 2] = arrn2;
                return arrobject2;
            }

            final int[] lessThanPoints(Generator generator3, int[] arrn3, int[] arrn2, int n) {
                int n2;
                int n3;
                int n4;
                int n5 = 0;
                if (arrn3 == null) {
                    arrn3 = (int[])generator3.gen();
                }
                while (arrn3 != null && (n3 = (n2 = arrn3[0]) + (n4 = arrn3[1])) <= n + 2) {
                    arrn2[n5++] = n2;
                    arrn2[n5++] = n4;
                    if (n3 > n) break;
                    arrn3 = (int[])generator3.gen();
                }
                arrn2[n5++] = Integer.MAX_VALUE;
                arrn2[n5++] = Integer.MAX_VALUE;
                this.upto = n5;
                return arrn3;
            }
        };
    }

    public static void reverseLessThanCoordinates(int[] arrn, int[] arrn2, int n, int n2) {
        int n3 = 0;
        while (arrn[n3] != Integer.MAX_VALUE) {
            n3 += 2;
        }
        int n4 = n3 - 1;
        for (int i = 0; i < n3; i += 2) {
            arrn2[n4 - 1] = n - arrn[i] + 1;
            arrn2[n4] = n2 - arrn[i + 1] + 1;
            n4 -= 2;
        }
        arrn2[n3] = Integer.MAX_VALUE;
        arrn2[n3 + 1] = Integer.MAX_VALUE;
    }

    public static void transformCoordinates(int[] arrn, int[] arrn2, int n, int n2) {
        int n3 = 0;
        while (arrn[n3] != Integer.MAX_VALUE) {
            arrn2[n3] = arrn[n3] + n;
            arrn2[n3 + 1] = arrn[n3 + 1] + n2;
            n3 += 2;
        }
        arrn2[n3] = Integer.MAX_VALUE;
        arrn2[n3 + 1] = Integer.MAX_VALUE;
    }

    public static Generator clipBoundariesOfDiagonalList(final Generator generator, final int n, final int n2, final int n3, final int n4) {
        return new Generator(){

            public Object gen() {
                Node node = (Node)generator.gen();
                if (node != null) {
                    if (node.y < n3) {
                        if (node.yMax < n3) {
                            return this.gen();
                        }
                        node.x += n3 - node.y;
                        node.y = n3;
                    }
                    if (node.yMax >= n4) {
                        if (node.y >= n4) {
                            return this.gen();
                        }
                        node.yMax = n4 - 1;
                    }
                    int n5 = node.x + node.yMax - node.y;
                    if (node.x < n) {
                        if (n5 < n) {
                            return this.gen();
                        }
                        node.y += n - node.x;
                        node.x = n;
                    }
                    if (n5 >= n2) {
                        if (node.x >= n2) {
                            return this.gen();
                        }
                        node.yMax -= n5 - n2 + 1;
                    }
                    return node;
                }
                return null;
            }
        };
    }
    public static class Node
    implements Cloneable,
    Comparable {
        public int x;
        public int y;
        public int yMax;
        public int z;

        public Node(int n, int n2, int n3, int n4) {
            this.x = n;
            this.y = n2;
            this.yMax = n3;
            this.z = n4;
        }

        public int compareTo(Object object) {
            Node node = (Node)object;
            return this.y < node.y ? -1 : (this.y > node.y ? 1 : (this.x < node.x ? -1 : (this.x > node.x ? 1 : 0)));
        }

        public Object clone() {
            try {
                return super.clone();
            }
            catch (CloneNotSupportedException cloneNotSupportedException) {
                cloneNotSupportedException.printStackTrace();
                throw new IllegalStateException();
            }
        }

        public String toString() {
            return " ( " + this.x + " " + this.y + " " + this.yMax + " " + this.z + " ) ";
        }
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan.utils;

import bp.common.ds.Array;
import bp.common.fp.Functions_2Args;
import bp.common.fp.Generator;
import bp.common.fp.GeneratorTools;
import bp.common.fp.Generators;
import bp.common.fp.IterationTools;
import bp.common.fp.Procedure_Int;
import bp.common.io.FastaOutput_Procedure_Int;
import bp.common.io.FastaParser_Generator_Int;
import bp.common.io.InputMunger;
import bp.common.io.NewickTreeParser;
import bp.pecan.Chains;
import bp.pecan.PecanTools;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class PrePecan {
    static final Logger logger = Logger.getLogger(PrePecan.class.getName());
    public static final String TREE = "TREE";
    public static final String SEQUENCES = "SEQUENCES";
    public static final String OUTPUT_FILE = "OUTPUT_FILE";
    public static final String EXONERATE_WORD_LENGTHS = "EXONERATE_WORD_LENGTHS";
    public static final String EXONERATE_MIN_SCORES = "EXONERATE_MIN_SCORES";
    public static final String EXONERATE_SOFT_MASK_SEQUENCES = "EXONERATE_SOFT_MASK_SEQUENCES";
    public static final String EXONERATE_SATURATE_THRESHOLD = "EXONERATE_SATURATE_THRESHOLD";
    public static final String EXONERATE_GAPPED_EXTENSION = "EXONERATE_GAPPED_EXTENSION";
    public static final String EXONERATE_BASIC_COMMAND = "EXONERATE_BASIC_COMMAND";
    public static final String EXONERATE_MAX_DISTANCES = "EXONERATE_MAX_DISTANCE";
    public static final String EXONERATE_MODELS = "EXONERATE_MODELS";
    public static final String EXONERATE_MIN_DISTANCE = "EXONERATE_MIN_DISTANCE";
    public static final String EXONERATE_STRING = "EXONERATE_STRING";
    public static final String CONSISTENCY_TRANSFORM = "CONSISTENCY_TRANSFORM";
    public static final String EDGE_TRIM = "EDGE_TRIM";
    public static final String RESCORE_ALIGNMENTS = "RESCORE_ALIGNMENTS";
    public static final String RELATIVE_ENTROPY_THRESHOLD = "RELATIVE_ENTROPY_THRESHOLD";
    public static final String CODING_2_CODING = "CODING_2_CODING";
    public String[] fastaIDs;
    public int[] seqSizes;
    public byte[][] cachedSeqFiles;
    public String outputFile = "output.mfa";
    public int[] exonerateWordLengths = new int[]{5, 8, 11};
    public int[] exonerateMinScores = new int[]{100, 150, 200};
    public boolean[] softMask = new boolean[]{false, true, true};
    public int saturateThreshold = 8;
    public boolean[] gappedExtension = new boolean[]{false, false, false};
    public int[] maxDistances = new int[]{20000, 664000, Integer.MAX_VALUE};
    public int minDistance = 200;
    public int edgeTrim = 3;
    public String[] exonerateModels = new String[]{"affine:local", "affine:local", "affine:local"};
    public String[] exonerateBasicCommand = new String[]{"--showcigar", "true", "--showvulgar", "false", "--showalignment", "false", "--querytype", "dna", "--targettype", "dna"};
    public String exonerateString = "exonerate";
    public boolean[] consistencyTransform = new boolean[]{false, true, true};
    public boolean rescoreAlignments = false;
    public float relativeEntropyThreshold = 0.65f;
    public boolean coding2Coding = false;
    public int coding2CodingMinScore = 250;
    public int exoneratePartitionAlignerWordLength = 6;
    public int exoneratePartitionAlignerMinScore = 0;
    public boolean exoneratePartitionAlignerGappedExtension = true;
    public String exoneratePartitionAlignerModel = "affine:local";
    public boolean exoneratePartitionAlignerSoftMaskSequences = false;

    public InputMunger setCommandLineArguments(InputMunger inputMunger) {
        inputMunger.noInputString("PrePecan : \nFor the given sequences and tree, create an alignment using the pre-Pecan chaining routines and Exonerate as pair alignment generator");
        inputMunger.addWatch(TREE, 1, "Newick tree for sequences, unspecified distances are given the value 1.0");
        inputMunger.addWatch_VariableTermsLength(SEQUENCES, "Sequence files in fasta format");
        inputMunger.addWatch(OUTPUT_FILE, 1, "File in which to write multi-fasta formatted output, default : " + this.outputFile);
        inputMunger.addWatch_VariableTermsLength(EXONERATE_WORD_LENGTHS, "Word length of Exonerate hits for recursive divide and conquer with more leniant parameters, default : " + IterationTools.join(this.exonerateWordLengths, " "));
        inputMunger.addWatch_VariableTermsLength(EXONERATE_BASIC_COMMAND, "Basic command upon which exonerate is run, default : " + IterationTools.join(this.exonerateBasicCommand, " "));
        inputMunger.addWatch(EXONERATE_STRING, 1, "Path to exonerate, default : " + this.exonerateString);
        inputMunger.addWatch_VariableTermsLength(CONSISTENCY_TRANSFORM, "Consistency transform the chains between sequences, default : " + IterationTools.join(this.consistencyTransform, " "));
        inputMunger.addWatch(EDGE_TRIM, 1, "Amount of edge to trim from diagonals, default : " + this.edgeTrim);
        inputMunger.addWatch(RESCORE_ALIGNMENTS, 0, "Rescore alignments, default (flip): " + this.rescoreAlignments);
        inputMunger.addWatch_VariableTermsLength(EXONERATE_MIN_SCORES, "Exonerate min scores for recursive divide and conquer with more leniant parameters, default : " + IterationTools.join(this.exonerateMinScores, " "));
        inputMunger.addWatch_VariableTermsLength(EXONERATE_SOFT_MASK_SEQUENCES, "Tell Exonerate sequences are softmasked for recursive divide and conquer with more leniant parameters, default : " + IterationTools.join(this.softMask, " "));
        inputMunger.addWatch(EXONERATE_SATURATE_THRESHOLD, 1, "Exonerate saturate threshold, default : " + this.saturateThreshold);
        inputMunger.addWatch_VariableTermsLength(EXONERATE_GAPPED_EXTENSION, "Use Exonerate gapped extension mode for recursive divide and conquer with more leniant parameters, default : " + IterationTools.join(this.gappedExtension, " "));
        inputMunger.addWatch_VariableTermsLength(EXONERATE_MAX_DISTANCES, "Max distances for recursive divide and conquer with more leniant parameters, default : " + IterationTools.join(this.maxDistances, " "));
        inputMunger.addWatch_VariableTermsLength(EXONERATE_MODELS, "Exonerate models for recursive divide and conquer with more leniant parameters, default : " + IterationTools.join(this.exonerateModels, " "));
        inputMunger.addWatch(EXONERATE_MIN_DISTANCE, 1, "Min distance for exonerate, default : " + this.minDistance);
        inputMunger.addWatch(RELATIVE_ENTROPY_THRESHOLD, 1, "Relative entropy threshold below which alignments are discarded, default : " + this.relativeEntropyThreshold);
        inputMunger.addWatch(CODING_2_CODING, 0, "Perform pre-alignment with coding2coding model, default : " + this.coding2Coding);
        return inputMunger;
    }

    public void parseArguments(InputMunger inputMunger) {
        if (inputMunger.watchSet(EXONERATE_WORD_LENGTHS)) {
            this.exonerateWordLengths = Array.convertToInts(inputMunger.watchStrings(EXONERATE_WORD_LENGTHS));
        }
        if (inputMunger.watchSet(EXONERATE_BASIC_COMMAND)) {
            this.exonerateBasicCommand = inputMunger.watchStrings(EXONERATE_BASIC_COMMAND);
        }
        this.exonerateString = inputMunger.parseValue(this.exonerateString, EXONERATE_STRING);
        if (inputMunger.watchSet(CONSISTENCY_TRANSFORM)) {
            this.consistencyTransform = Array.convertToBooleans(inputMunger.watchStrings(CONSISTENCY_TRANSFORM));
        }
        this.edgeTrim = inputMunger.parseValue(this.edgeTrim, EDGE_TRIM);
        this.outputFile = inputMunger.parseValue(this.outputFile, OUTPUT_FILE);
        if (inputMunger.watchSet(RESCORE_ALIGNMENTS)) {
            boolean bl = this.rescoreAlignments = !this.rescoreAlignments;
        }
        if (inputMunger.watchSet(EXONERATE_MIN_SCORES)) {
            this.exonerateMinScores = Array.convertToInts(inputMunger.watchStrings(EXONERATE_MIN_SCORES));
        }
        if (inputMunger.watchSet(EXONERATE_SOFT_MASK_SEQUENCES)) {
            this.softMask = Array.convertToBooleans(inputMunger.watchStrings(EXONERATE_SOFT_MASK_SEQUENCES));
        }
        this.saturateThreshold = inputMunger.parseValue(this.saturateThreshold, EXONERATE_SATURATE_THRESHOLD);
        if (inputMunger.watchSet(EXONERATE_GAPPED_EXTENSION)) {
            this.gappedExtension = Array.convertToBooleans(inputMunger.watchStrings(EXONERATE_GAPPED_EXTENSION));
        }
        if (inputMunger.watchSet(EXONERATE_MAX_DISTANCES)) {
            this.maxDistances = Array.convertToInts(inputMunger.watchStrings(EXONERATE_MAX_DISTANCES));
        }
        if (inputMunger.watchSet(EXONERATE_MODELS)) {
            this.exonerateModels = inputMunger.watchStrings(EXONERATE_MODELS);
        }
        this.minDistance = inputMunger.parseValue(this.minDistance, EXONERATE_MIN_DISTANCE);
        this.relativeEntropyThreshold = (float)inputMunger.parseValue(this.relativeEntropyThreshold, RELATIVE_ENTROPY_THRESHOLD);
        if (inputMunger.watchSet(CODING_2_CODING)) {
            this.coding2Coding = !this.coding2Coding;
        }
    }

    public List getSeqSizes(String[] arrstring) throws IOException {
        int n = arrstring.length;
        String[] arrstring2 = new String[n];
        int[] arrn = new int[n];
        byte[][] arrarrby = new byte[n][];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            int n3;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(arrstring[i]));
            FastaParser_Generator_Int fastaParser_Generator_Int = new FastaParser_Generator_Int(bufferedInputStream, Integer.MAX_VALUE);
            arrstring2[i] = fastaParser_Generator_Int.getFastaID();
            int n4 = 0;
            byte[] arrby = new byte[(int)new File(arrstring[i]).length()];
            while ((n3 = fastaParser_Generator_Int.gen()) != Integer.MAX_VALUE) {
                arrby[n4++] = (byte)n3;
            }
            arrarrby[i] = arrby;
            arrn[i] = n4;
            logger.info(" Sequence " + arrstring2[i] + " is of length " + arrn[i] + " " + n2);
            n2 += n4;
            ((InputStream)bufferedInputStream).close();
        }
        LinkedList<Object> linkedList = new LinkedList<Object>();
        linkedList.add(arrstring2);
        linkedList.add(arrn);
        linkedList.add(arrarrby);
        return linkedList;
    }

    public Chains.PrimeConstraints runPrePecan(NewickTreeParser.Node node, String[] arrstring) throws IOException {
        int n;
        int[][] arrn;
        long l = System.currentTimeMillis();
        PecanTools.replaceEdgeLengths(node, Double.MIN_VALUE, 1.0);
        logger.info("Parsed tree : " + node);
        int n2 = arrstring.length;
        List list = this.getSeqSizes(arrstring);
        this.fastaIDs = (String[])list.get(0);
        this.seqSizes = (int[])list.get(1);
        this.cachedSeqFiles = (byte[][])list.get(2);
        int[] arrn2 = (int[])this.seqSizes.clone();
        Arrays.sort(arrn2);
        int n3 = arrn2[n2 - 1] + arrn2[n2 - 2];
        for (n = 0; n < this.maxDistances.length && n3 >= this.maxDistances[n]; ++n) {
        }
        arrn2 = PecanTools.getDistances(node, n2, Functions_2Args.sum());
        for (n3 = 0; n3 < arrn2.length; ++n3) {
            logger.info("Sequence distances for " + n3 + " " + IterationTools.join((double[])arrn2[n3], " "));
        }
        for (int[] arrn3 : arrn = PecanTools.getPairOrdering((double[][])arrn2)) {
            logger.info("Pair ordering (ascending distance) : " + IterationTools.join(arrn3, " "));
        }
        int[][] arrn4 = new Chains.Aligner[n + 1];
        for (int i = 0; i < arrn4.length; ++i) {
            Chains.LocalAligner localAligner = PrePecan.makeExonerateAligner(this.exonerateBasicCommand, this.exonerateString, this.exonerateWordLengths[i], this.exonerateMinScores[i], this.gappedExtension[i], this.exonerateModels[i], this.saturateThreshold, this.softMask[i], this.softMask[i]);
            if (this.rescoreAlignments) {
                localAligner = Chains.rescoreAlignments(localAligner, this.relativeEntropyThreshold);
            }
            arrn4[i] = Chains.alignerConvertor(localAligner);
        }
        if (this.coding2Coding) {
            Chains.LocalAligner localAligner = PrePecan.makeExonerateAligner(this.exonerateBasicCommand, this.exonerateString, 11, this.coding2CodingMinScore, false, "coding2coding", this.saturateThreshold, true, true);
            if (this.rescoreAlignments) {
                localAligner = Chains.rescoreAlignments(localAligner, this.relativeEntropyThreshold);
            }
            Chains.Aligner[] arraligner = new Chains.Aligner[arrn4.length + 1];
            arraligner[arrn4.length] = Chains.alignerConvertor(localAligner);
            System.arraycopy(arrn4, 0, arraligner, 0, arrn4.length);
            arrn4 = arraligner;
            this.maxDistances = Array.concatenate(this.maxDistances, new int[]{Integer.MAX_VALUE});
            this.consistencyTransform = Array.concatenate(this.consistencyTransform, new boolean[]{true});
            logger.info(arrn4.length + " " + ++n);
        }
        Chains.PrimeConstraints primeConstraints = new Chains.PrimeConstraints(n2);
        Chains.makeConsistentChains(primeConstraints, arrn, n2, (Chains.Aligner[])arrn4, n, this.minDistance, this.maxDistances, this.cachedSeqFiles, this.seqSizes, this.consistencyTransform, this.edgeTrim);
        logger.info("Total time taken for Pre_Pecan alignment " + (double)(System.currentTimeMillis() - l) / 1000.0);
        return primeConstraints;
    }

    public static String makeStarTreeP(int n) {
        assert (n > 0);
        if (n >= 2) {
            return "(" + PrePecan.makeStarTreeP(n / 2) + ":1.0," + PrePecan.makeStarTreeP(n / 2 + n % 2) + ":1.0)";
        }
        return "A";
    }

    public static String makeStarTree(int n) {
        return PrePecan.makeStarTreeP(n) + ";";
    }

    public static Chains.LocalAligner makeExonerateAligner(String[] arrstring, String string, int n, int n2, boolean bl, String string2, int n3, boolean bl2, boolean bl3) {
        String[] arrstring2 = arrstring;
        Object[] arrobject = new String[arrstring2.length + 15];
        arrobject[0] = string;
        System.arraycopy(arrstring2, 0, arrobject, 1, arrstring2.length);
        arrobject[arrstring2.length + 1] = "--dnawordlen";
        arrobject[arrstring2.length + 2] = n + "";
        arrobject[arrstring2.length + 3] = "--score";
        arrobject[arrstring2.length + 4] = n2 + "";
        arrobject[arrstring2.length + 5] = "--gappedextension";
        arrobject[arrstring2.length + 6] = bl + "";
        arrobject[arrstring2.length + 7] = "--model";
        arrobject[arrstring2.length + 8] = string2;
        arrobject[arrstring2.length + 9] = "--saturatethreshold";
        arrobject[arrstring2.length + 10] = n3 + "";
        arrobject[arrstring2.length + 11] = "--softmasktarget";
        arrobject[arrstring2.length + 12] = bl2 + "";
        arrobject[arrstring2.length + 13] = "--softmaskquery";
        arrobject[arrstring2.length + 14] = bl3 + "";
        logger.info("Creating exonerate aligner : " + IterationTools.join(arrobject, " "));
        return Chains.makeExonerateAlignment((String[])arrobject);
    }

    public static void main(String[] arrstring) throws IOException {
        PrePecan prePecan = new PrePecan();
        InputMunger inputMunger = new InputMunger();
        inputMunger.addStandardWatches();
        prePecan.setCommandLineArguments(inputMunger);
        if (!inputMunger.parseInput(arrstring)) {
            System.exit(0);
        }
        inputMunger.processStandardWatches();
        prePecan.parseArguments(inputMunger);
        String[] arrstring2 = inputMunger.watchStrings(SEQUENCES);
        int n = arrstring2.length;
        String string = inputMunger.watchSet(TREE) ? inputMunger.watchStrings(TREE)[0] : PrePecan.makeStarTree(n);
        NewickTreeParser.Node node = new NewickTreeParser((Generator)NewickTreeParser.commentEater((Generator)NewickTreeParser.tokenise((Reader)new StringReader((String)string)))).tree;
        Chains.PrimeConstraints primeConstraints = prePecan.runPrePecan(node, arrstring2);
        Generator[][] arrgenerator = new Generator[n][n];
        for (int i = 0; i < arrgenerator.length; ++i) {
            for (int j = i + 1; j < arrgenerator.length; ++j) {
                List list = (List)GeneratorTools.append(primeConstraints.convertPrimeConstraintsToEdgeList(i, j, true), new LinkedList());
                arrgenerator[i][j] = Generators.iteratorGenerator(list.iterator());
                logger.info("Number of edges for i : " + i + ", j : " + j + " : " + list.size());
                arrgenerator[j][i] = primeConstraints.convertPrimeConstraintsToEdgeList(j, i, true);
            }
        }
        File[] arrfile = new File[n];
        Procedure_Int[] arrprocedure_Int = new FastaOutput_Procedure_Int[n];
        for (int i = 0; i < prePecan.fastaIDs.length; ++i) {
            File file = File.createTempFile("tmp_output_", ".fa");
            logger.info(" Temp output file for : " + prePecan.fastaIDs[i] + " is : " + file.toString());
            arrfile[i] = file;
            arrprocedure_Int[i] = new FastaOutput_Procedure_Int(new BufferedOutputStream(new FileOutputStream(file)), prePecan.fastaIDs[i]);
        }
        Chains.makeMultipleAlignment(arrprocedure_Int, arrgenerator, prePecan.cachedSeqFiles, prePecan.seqSizes, n, new int[100000], (byte)45);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(prePecan.outputFile));
        for (int i = 0; i < arrprocedure_Int.length; ++i) {
            int n2;
            ((FastaOutput_Procedure_Int)arrprocedure_Int[i]).endAndClose();
            FastaParser_Generator_Int fastaParser_Generator_Int = new FastaParser_Generator_Int(new BufferedInputStream(new FileInputStream(arrfile[i])), Integer.MAX_VALUE);
            FastaOutput_Procedure_Int fastaOutput_Procedure_Int = new FastaOutput_Procedure_Int(bufferedOutputStream, fastaParser_Generator_Int.getFastaID());
            while ((n2 = fastaParser_Generator_Int.gen()) != Integer.MAX_VALUE) {
                fastaOutput_Procedure_Int.pro(n2);
            }
            fastaOutput_Procedure_Int.end();
            arrfile[i].delete();
        }
    }
}

/*
 * Decompiled with CFR 0.148.
 */
package bp.pecan;

import bp.common.ds.IntCrapHash;
import bp.common.ds.ScrollingQueue_Int;
import bp.common.ds.wrappers.MutableInteger;
import bp.common.fp.Function;
import bp.common.fp.Function_Int_2Args;
import bp.common.fp.Function_Int_3Args;
import bp.common.fp.Generator;
import bp.common.fp.IterationTools;
import bp.common.fp.Iterators;
import bp.common.fp.Procedure;
import bp.common.fp.Procedure_2Args;
import bp.common.fp.Procedure_Int_3Args;
import bp.pecan.Librarian;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class WeightTranslator {
    WT rowWeightTranslator;
    WT columnWeightTranslator;

    public WeightTranslator(Librarian.WeightsGetter weightsGetter, Function_Int_3Args function_Int_3Args, int[] arrn, int[] arrn2, int[] arrn3, int[] arrn4, int n, Function_Int_2Args function_Int_2Args, Procedure_2Args procedure_2Args, Procedure_Int_3Args procedure_Int_3Args, int[] arrn5) {
        int n2;
        ScrollingQueue_Int[] arrscrollingQueue_Int = new ScrollingQueue_Int[arrn.length];
        for (n2 = 0; n2 < arrscrollingQueue_Int.length; ++n2) {
            arrscrollingQueue_Int[n2] = new ScrollingQueue_Int(n, arrn3[n2 * 2], true);
        }
        ScrollingQueue_Int[] arrscrollingQueue_Int2 = new ScrollingQueue_Int[arrn2.length];
        for (n2 = 0; n2 < arrscrollingQueue_Int2.length; ++n2) {
            arrscrollingQueue_Int2[n2] = new ScrollingQueue_Int(n, arrn4[n2 * 2], true);
        }
        this.rowWeightTranslator = new WT(arrscrollingQueue_Int, arrscrollingQueue_Int2, arrn, arrn2, weightsGetter, function_Int_3Args, function_Int_2Args, procedure_2Args, procedure_Int_3Args, arrn5);
        this.columnWeightTranslator = new WT(arrscrollingQueue_Int2, arrscrollingQueue_Int, arrn2, arrn, weightsGetter, function_Int_3Args, function_Int_2Args, procedure_2Args, procedure_Int_3Args, arrn5);
    }

    public Procedure getInputX() {
        return this.rowWeightTranslator;
    }

    public Procedure getInputY() {
        return this.columnWeightTranslator;
    }

    public Generator getOutputX() {
        return this.rowWeightTranslator;
    }

    public Generator getOutputY() {
        return this.columnWeightTranslator;
    }
    static class WT
    implements Procedure,
    Generator {
        static final MutableInteger mI = new MutableInteger();
        final List d;
        final Librarian.WeightsGetter getWeightsForResidue;
        final Function_Int_3Args getMaxResidue;
        final Procedure_Int_3Args releaseWeights;
        final Function_Int_2Args adder;
        final ScrollingQueue_Int[] residueMap;
        final ScrollingQueue_Int[] oppositeResidueMap;
        final int[] seqs;
        final int[] oppositeSeqs;
        final int[] maximumResidues;
        final int[] column;
        final int[] scratchColumn;
        int maximumResiduesIndex;
        int offsetIndex = 0;
        final IntCrapHash weightMap = new IntCrapHash(200);
        final Procedure_2Args convertInput;
        final int[] scratchA;

        static final void addToResidueMap(ScrollingQueue_Int[] arrscrollingQueue_Int, Iterator iterator, int n) {
            int n2 = 0;
            while (iterator.hasNext()) {
                int n3 = n2 + n;
                int[] arrn = (int[])iterator.next();
                for (int i = 0; i < arrn.length; ++i) {
                    if (arrn[i] == Integer.MAX_VALUE) continue;
                    if (arrn[i] != arrscrollingQueue_Int[i].lastIndex()) {
                        throw new IllegalStateException("Residue being added is out of sync with residue map");
                    }
                    arrscrollingQueue_Int[i].add(n3);
                }
                ++n2;
            }
        }

        static final int[] convertToWeightArray(IntCrapHash intCrapHash) {
            int[] arrn = new int[intCrapHash.size() * 2];
            intCrapHash.getEntries(arrn);
            return arrn;
        }

        static final void getMaxResidues(int[] arrn, int[] arrn2, int[] arrn3, int[] arrn4, Function_Int_3Args function_Int_3Args) {
            for (int i = 0; i < arrn.length; ++i) {
                if (arrn[i] == Integer.MAX_VALUE) continue;
                int n = arrn[i];
                int n2 = arrn3[i];
                for (int j = 0; j < arrn4.length; ++j) {
                    int n3 = function_Int_3Args.fn(n, n2, arrn4[j]);
                    if (n3 == Integer.MAX_VALUE || n3 <= arrn2[j]) continue;
                    arrn2[j] = n3;
                }
            }
        }

        static final void doLine(int[] arrn, IntCrapHash intCrapHash, int[] arrn2, int[] arrn3, ScrollingQueue_Int[] arrscrollingQueue_Int, Librarian.WeightsGetter weightsGetter, int[] arrn4, Function_Int_2Args function_Int_2Args, Procedure_Int_3Args procedure_Int_3Args) {
            for (int i = 0; i < arrn.length; ++i) {
                if (arrn[i] == Integer.MAX_VALUE) continue;
                int n = arrn[i];
                int n2 = arrn2[i];
                for (int j = 0; j < arrn3.length; ++j) {
                    int n3 = arrn3[j];
                    int n4 = weightsGetter.fn(n, n2, n3, arrn4, 0);
                    procedure_Int_3Args.pro(n, n2, n3);
                    WT.doLine(arrn4, n4, intCrapHash, arrscrollingQueue_Int[j], function_Int_2Args);
                }
            }
        }

        static final void doLine(int[] arrn, int n, IntCrapHash intCrapHash, ScrollingQueue_Int scrollingQueue_Int, Function_Int_2Args function_Int_2Args) {
            for (int i = 0; i < n && scrollingQueue_Int.firstIndex() <= arrn[i]; i += 2) {
                WT.mI.i = scrollingQueue_Int.get(arrn[i]);
                intCrapHash.put(scrollingQueue_Int.get(arrn[i]), arrn[i + 1], function_Int_2Args);
            }
        }

        static final void removeFirstPositions(ScrollingQueue_Int[] arrscrollingQueue_Int, int[] arrn) {
            for (int i = 0; i < arrn.length; ++i) {
                if (arrn[i] == Integer.MAX_VALUE) continue;
                if (arrn[i] != arrscrollingQueue_Int[i].firstIndex()) {
                    throw new IllegalStateException("Residue map out of sync with alignment");
                }
                arrscrollingQueue_Int[i].removeFirst();
            }
        }

        WT(ScrollingQueue_Int[] arrscrollingQueue_Int, ScrollingQueue_Int[] arrscrollingQueue_Int2, int[] arrn, int[] arrn2, Librarian.WeightsGetter weightsGetter, Function_Int_3Args function_Int_3Args, Function_Int_2Args function_Int_2Args, Procedure_2Args procedure_2Args, Procedure_Int_3Args procedure_Int_3Args, int[] arrn3) {
            this.d = new LinkedList();
            this.residueMap = arrscrollingQueue_Int;
            this.seqs = arrn;
            this.oppositeSeqs = arrn2;
            this.oppositeResidueMap = arrscrollingQueue_Int2;
            this.maximumResidues = new int[arrscrollingQueue_Int2.length];
            Arrays.fill(this.maximumResidues, Integer.MIN_VALUE);
            this.maximumResiduesIndex = this.maximumResidues.length;
            this.getWeightsForResidue = weightsGetter;
            this.adder = function_Int_2Args;
            this.convertInput = procedure_2Args;
            this.column = new int[arrscrollingQueue_Int.length];
            this.scratchColumn = new int[arrscrollingQueue_Int.length];
            this.releaseWeights = procedure_Int_3Args;
            this.scratchA = arrn3;
            this.getMaxResidue = function_Int_3Args;
        }

        public Object gen() {
            if (this.maximumResiduesIndex == this.maximumResidues.length) {
                if (this.d.size() == 0) {
                    return null;
                }
                this.maximumResiduesIndex = 0;
                this.convertInput.pro(this.d.get(0), this.column);
                WT.getMaxResidues(this.column, this.maximumResidues, this.seqs, this.oppositeSeqs, this.getMaxResidue);
            }
            do {
                if (this.oppositeResidueMap[this.maximumResiduesIndex].lastIndex() > this.maximumResidues[this.maximumResiduesIndex]) {
                    ++this.maximumResiduesIndex;
                    continue;
                }
                return null;
            } while (this.maximumResiduesIndex < this.maximumResidues.length);
            WT.doLine(this.column, this.weightMap, this.seqs, this.oppositeSeqs, this.oppositeResidueMap, this.getWeightsForResidue, this.scratchA, this.adder, this.releaseWeights);
            int[] arrn = WT.convertToWeightArray(this.weightMap);
            this.convertInput.pro(this.d.remove(0), this.scratchColumn);
            WT.removeFirstPositions(this.residueMap, this.scratchColumn);
            this.weightMap.clear();
            return arrn;
        }

        public void pro(Object object) {
            int n = this.d.size();
            IterationTools.append(((List)object).iterator(), this.d);
            WT.addToResidueMap(this.residueMap, Iterators.map(this.d.listIterator(n), new Function(){

                public Object fn(Object object) {
                    WT.this.convertInput.pro(object, WT.this.scratchColumn);
                    return WT.this.scratchColumn;
                }
            }), this.offsetIndex);
            this.offsetIndex += this.d.size() - n;
        }

    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.trawler;

import bp.common.fp.Function_Int;
import bp.common.fp.Predicate_Int;
import bp.common.io.FastaParser_Generator_Int;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphabet {
    public final Pattern legalAlphabetPattern = Pattern.compile("A?C?G?T?M?R?W?S?Y?K?N?");
    public final byte TERMINAL = (byte)16;
    public final byte WILDCARD = (byte)15;
    public final int[] nucleotideIC = new int[]{Integer.MAX_VALUE, 0, 0, 1, 0, 1, 1, Integer.MAX_VALUE, 0, 1, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, 2};
    public final Function_Int charToByte = new Function_Int(){
        int[] bA = new int[256];
        {
            Arrays.fill(this.bA, Integer.MAX_VALUE);
            this.bA[65] = 1;
            this.bA[67] = 2;
            this.bA[71] = 4;
            this.bA[84] = 8;
            this.bA[77] = 3;
            this.bA[82] = 5;
            this.bA[87] = 9;
            this.bA[83] = 6;
            this.bA[89] = 10;
            this.bA[75] = 12;
            this.bA[78] = 15;
        }

        public int fn(int n) {
            return this.bA[n];
        }
    };
    public final Predicate_Int validByte = new Predicate_Int(){

        public boolean test(int n) {
            return (n | 0xF) == 15;
        }
    };
    public final Predicate_Int validChar = new Predicate_Int(){

        public boolean test(int n) {
            return Alphabet.this.validByte.test(Alphabet.this.charToByte.fn(n));
        }
    };
    public final Function_Int byteToChar = new Function_Int(){
        int[] bA = new int[17];
        {
            this.bA[0] = Integer.MAX_VALUE;
            this.bA[1] = 65;
            this.bA[2] = 67;
            this.bA[4] = 71;
            this.bA[8] = 84;
            this.bA[3] = 77;
            this.bA[5] = 82;
            this.bA[9] = 87;
            this.bA[6] = 83;
            this.bA[10] = 89;
            this.bA[12] = 75;
            this.bA[15] = 78;
        }

        public int fn(int n) {
            return this.bA[n];
        }
    };

    public static byte[] readInString(String string, byte by, boolean bl) throws IOException {
        int n = 1;
        if (bl) {
            n = 2;
        }
        byte[] arrby = new byte[(int)new File(string).length() * n];
        FastaParser_Generator_Int fastaParser_Generator_Int = new FastaParser_Generator_Int(new BufferedInputStream(new FileInputStream(string)), Integer.MAX_VALUE);
        int n2 = 0;
        do {
            int n3 = FastaParser_Generator_Int.readFile(fastaParser_Generator_Int, Integer.MAX_VALUE, arrby, n2);
            arrby[n3++] = by;
            if (bl) {
                for (int i = 0; i < n3 - n2; ++i) {
                    arrby[n3++] = arrby[n2++];
                }
            }
            n2 = n3;
        } while (fastaParser_Generator_Int.isNewSequence());
        byte[] arrby2 = new byte[n2];
        System.arraycopy(arrby, 0, arrby2, 0, n2);
        return arrby2;
    }

    public static void translateString(byte[] arrby, byte by, byte by2, Function_Int function_Int, byte by3, byte by4) {
        for (int i = 0; i < arrby.length; ++i) {
            int n = function_Int.fn(arrby[i]);
            if (n == Integer.MAX_VALUE) {
                if (arrby[i] == by) {
                    arrby[i] = by2;
                    continue;
                }
                arrby[i] = (byte)((double)by3 + Math.random() * (double)by4);
                continue;
            }
            arrby[i] = (byte)n;
        }
    }

    static final Object[] parseAlphabet(String string, Pattern pattern, Function_Int function_Int, int[] arrn) {
        if (!pattern.matcher(string).matches()) {
            throw new IllegalArgumentException("Given alphabet is invalid " + string);
        }
        byte[] arrby = string.getBytes();
        int[] arrn2 = new int[arrby.length];
        for (int i = 0; i < arrby.length; ++i) {
            int n = function_Int.fn(arrby[i]);
            arrby[i] = (byte)n;
            arrn2[i] = arrn[n];
        }
        return new Object[]{arrby, arrn2};
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.trawler;

import bp.common.ds.SuffixTree;
import bp.common.fp.Function_Int;
import bp.common.fp.IterationTools;
import bp.common.fp.Predicate_Int;
import bp.common.io.Debug;
import bp.common.io.InputMunger;
import bp.trawler.Alphabet;
import bp.trawler.TrawlerStatistics;
import bp.trawler.TrawlerTools;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Trawler {
    static final Logger logger = Logger.getLogger(Trawler.class.getName());
    public static final String CONSERVED_FILE = "CONSERVED_FILE";
    public static final String BACKGROUND_FILE = "BACKGROUND_FILE";
    public static final String ALPHABET = "ALPHABET";
    public static final String MAXIMUM_MOTIF_SIZE = "MAXIMUM_MOTIF_SIZE";
    public static final String MAXIMUM_MISMATCHES = "MAXIMUM_MISMATCHES";
    public static final String MINIMUM_OCCURRENCES = "MINIMUM_OCCURRENCES";
    public static final String MINIMUM_ZSCORE = "MINIMUM_ZSCORE";
    public static final String MAXIMUM_ZSCORE = "MAXIMUM_ZSCORE";
    public static final String MAXIMUM_NUMBER_OF_MOTIFS_TO_REPORT = "MAXIMUM_NUMBER_OF_MOTIFS_TO_REPORT";
    public static final String BYTE_MULTIPLE = "BYTE_MULTIPLE";
    public static final String OUTPUT_FILE = "OUTPUT_FILE";
    public static final String ESTIMATE_MAXIMUM_ZSCORE = "ESTIMATE_MAXIMUM_ZSCORE";
    public static final String ESTIMATE_MINIMUM_ZSCORE = "ESTIMATE_MINIMUM_ZSCORE";
    public static final String USE_SHUFFLED_BACKGROUND_SET = "USE_SHUFFLED_BACKGROUND_SET";
    public static final String RUN_SCAN_FUNCTION = "RUN_SCAN_FUNCTION";
    public static final String FRAGMENT_SHUFFLE_SIZE = "FRAGMENT_SHUFFLE_SIZE";
    public static final String NUMBER_OF_TAIL_VALUES_TO_AVERAGE_FROM = "NUMBER_OF_TAIL_VALUES_TO_AVERAGE_FROM";
    public static final String NUMBER_OF_ZSCORE_ESTIMATION_ITERATIONS = "NUMBER_OF_ZSCORE_ESTIMATION_ITERATIONS";
    public static final String SEARCH_REVERSE_COMPLEMENT = "SEARCH_REVERSE_COMPLEMENT";
    public static final byte TERMINALCHAR = 36;
    public String workingAlphabet = "ACGTN";
    public int maximumMotifSize = 20;
    public int maximumMismatches = 2;
    public int minimumOccurrences = 20;
    public float minimumZScore = 10.0f;
    public float maximumZScore = Float.MAX_VALUE;
    public int maximumNumberOfMotifsToReport = 100000;
    public int byteMultiple = 24;
    public boolean estimateMinimumZScore = true;
    public boolean estimateMaximumZScore = false;
    public boolean runScanFunction = true;
    public boolean useShuffledBackgroundSet = true;
    public boolean searchReverseComplement = false;
    public int fragmentShuffleSize = 20;
    public int numberOfTailValuesToAverageFrom = 5;
    public int numberOfZScoreEstimationIterations = 1;
    public String outputFile = "output.motifs";
    public Alphabet alphabet = new Alphabet();

    public InputMunger setCommandLineArguments(String[] arrstring) throws IOException {
        InputMunger inputMunger = new InputMunger();
        inputMunger.noInputString("Trawler, [options and parameters]");
        inputMunger.helpString("Trawler :\nThis is a suffix tree based motif finder. \nThe output is of the following format.. \n[CONSERVED COUNT] [BACKGROUND COUNT] [Z SCORE] [MOTIF]\nTrawler only looks at upper case IUPAC characters.\nSoft masked characters are ignored as are any other characters.\nThe score is calculated assuming an approximately normal distribution with\nexpected values calculated from sequences and background count\nProgram written by Benedict Paten. Mail to bjp (AT) ebi.ac.uk");
        inputMunger.addStandardWatches();
        inputMunger.addWatch(CONSERVED_FILE, 1, "The fasta file containing conserved sequences");
        inputMunger.addWatch(BACKGROUND_FILE, 1, "The fasta file containing background sequences");
        inputMunger.addWatch(ALPHABET, 1, "Alphabet to use, give any unspaced array of non-redundant, \n2-redundant and the wild-card IUPAC characters in the following order ACGTMRWSYKN, \n(REMINDER : A C G T [AC]:M [AG]:R [AT]:W [CG]:S [CT]:Y [GT]:K [N]:ACGT) default : " + this.workingAlphabet);
        inputMunger.addWatch(MAXIMUM_MOTIF_SIZE, 1, "Maximum size of motif, default : " + this.maximumMotifSize);
        inputMunger.addWatch(MAXIMUM_MISMATCHES, 1, "Maximum number of mismatches (2 for an N, 1 for a 2-redundant character), default : " + this.maximumMismatches);
        inputMunger.addWatch(MINIMUM_OCCURRENCES, 1, "Minimum occurrences of motif in conserved sequence, default : " + this.minimumOccurrences);
        inputMunger.addWatch(MINIMUM_ZSCORE, 1, "Minimum z-score, default : " + this.minimumZScore);
        inputMunger.addWatch(MAXIMUM_NUMBER_OF_MOTIFS_TO_REPORT, 1, "Maximum number of motifs to report in run (to prevent filling a disk), default : " + this.maximumNumberOfMotifsToReport);
        inputMunger.addWatch(BYTE_MULTIPLE, 1, "Byte multiple, default : " + this.byteMultiple);
        inputMunger.addWatch(OUTPUT_FILE, 1, "Output file, default : " + this.outputFile);
        inputMunger.addWatch(MAXIMUM_ZSCORE, 1, "Maximum z-score, default : " + this.maximumZScore);
        inputMunger.addWatch(ESTIMATE_MINIMUM_ZSCORE, 0, "Estimate the minimum z-score, (flip) default : " + this.estimateMinimumZScore);
        inputMunger.addWatch(ESTIMATE_MAXIMUM_ZSCORE, 0, "Estimate the maximum z-score, (flip) default : " + this.estimateMaximumZScore);
        inputMunger.addWatch(NUMBER_OF_ZSCORE_ESTIMATION_ITERATIONS, 1, "Number of z-score estimation iterations default : " + this.numberOfZScoreEstimationIterations);
        inputMunger.addWatch(RUN_SCAN_FUNCTION, 0, "Run the actual scan function to find motifs, else no motifs reported, (flip) default : " + this.runScanFunction);
        inputMunger.addWatch(NUMBER_OF_TAIL_VALUES_TO_AVERAGE_FROM, 1, "Number of tail values to estimate cut off using, default : " + this.numberOfTailValuesToAverageFrom);
        inputMunger.addWatch(USE_SHUFFLED_BACKGROUND_SET, 0, "Use a shuffled background set for estimating the z-score, (flip) default : " + this.useShuffledBackgroundSet);
        inputMunger.addWatch(FRAGMENT_SHUFFLE_SIZE, 1, "Size of fragments with which to shuffle background, default : " + this.fragmentShuffleSize);
        inputMunger.addWatch(SEARCH_REVERSE_COMPLEMENT, 0, "Search the reverse complement sequence also, (flip) default : " + this.searchReverseComplement);
        if (!inputMunger.parseInput(arrstring)) {
            return null;
        }
        inputMunger.processStandardWatches();
        this.workingAlphabet = inputMunger.parseValue(this.workingAlphabet, ALPHABET);
        this.maximumMotifSize = inputMunger.parseValue(this.maximumMotifSize, MAXIMUM_MOTIF_SIZE);
        this.maximumMismatches = inputMunger.parseValue(this.maximumMismatches, MAXIMUM_MISMATCHES);
        this.minimumOccurrences = inputMunger.parseValue(this.minimumOccurrences, MINIMUM_OCCURRENCES);
        this.minimumZScore = (float)inputMunger.parseValue(this.minimumZScore, MINIMUM_ZSCORE);
        this.maximumZScore = (float)inputMunger.parseValue(this.maximumZScore, MAXIMUM_ZSCORE);
        this.maximumNumberOfMotifsToReport = inputMunger.parseValue(this.maximumNumberOfMotifsToReport, MAXIMUM_NUMBER_OF_MOTIFS_TO_REPORT);
        this.numberOfZScoreEstimationIterations = inputMunger.parseValue(this.numberOfZScoreEstimationIterations, NUMBER_OF_ZSCORE_ESTIMATION_ITERATIONS);
        this.byteMultiple = inputMunger.parseValue(this.byteMultiple, BYTE_MULTIPLE);
        this.outputFile = inputMunger.parseValue(this.outputFile, OUTPUT_FILE);
        if (inputMunger.watchSet(ESTIMATE_MINIMUM_ZSCORE)) {
            boolean bl = this.estimateMinimumZScore = !this.estimateMinimumZScore;
        }
        if (inputMunger.watchSet(ESTIMATE_MAXIMUM_ZSCORE)) {
            boolean bl = this.estimateMaximumZScore = !this.estimateMaximumZScore;
        }
        if (inputMunger.watchSet(RUN_SCAN_FUNCTION)) {
            this.runScanFunction = !this.runScanFunction;
        }
        this.numberOfTailValuesToAverageFrom = inputMunger.parseValue(this.numberOfTailValuesToAverageFrom, NUMBER_OF_TAIL_VALUES_TO_AVERAGE_FROM);
        this.fragmentShuffleSize = inputMunger.parseValue(this.fragmentShuffleSize, FRAGMENT_SHUFFLE_SIZE);
        if (inputMunger.watchSet(USE_SHUFFLED_BACKGROUND_SET)) {
            boolean bl = this.useShuffledBackgroundSet = !this.useShuffledBackgroundSet;
        }
        if (inputMunger.watchSet(SEARCH_REVERSE_COMPLEMENT)) {
            this.searchReverseComplement = !this.searchReverseComplement;
        }
        return inputMunger;
    }

    public float[] tailValues(byte[] arrby, byte[] arrby2, float[] arrf, boolean bl) {
        Object object;
        arrby = (byte[])arrby.clone();
        arrby2 = (byte[])arrby2.clone();
        if (this.useShuffledBackgroundSet) {
            logger.info("Using shuffled background set");
            object = (byte[])arrby2.clone();
            TrawlerStatistics.shuffleSequences((byte[])object, this.fragmentShuffleSize);
            TrawlerStatistics.replaceSequences(arrby, (byte[])object, this.alphabet.validChar);
        } else {
            logger.info("Not using shuffled background set");
        }
        object = new TreeSet();
        logger.info("Running trawler script to estimate cut off");
        this.runTrawler(arrby, arrby2, bl ? TrawlerStatistics.getMaxes(arrf, (SortedSet<Float>)object, this.numberOfTailValuesToAverageFrom) : TrawlerStatistics.getMins(arrf, (SortedSet<Float>)object, this.numberOfTailValuesToAverageFrom));
        float[] arrf2 = new float[object.size()];
        int n = 0;
        Iterator iterator = object.iterator();
        while (iterator.hasNext()) {
            arrf2[n++] = ((Float)iterator.next()).floatValue();
        }
        return arrf2;
    }

    public void runTrawler(byte[] arrby, byte[] arrby2, TrawlerTools.PassOut passOut) {
        logger.info("Starting Trawler script");
        long l = System.currentTimeMillis();
        Object object = Alphabet.parseAlphabet(this.workingAlphabet, this.alphabet.legalAlphabetPattern, this.alphabet.charToByte, this.alphabet.nucleotideIC);
        byte[] arrby3 = (byte[])object[0];
        int[] arrn = (int[])object[1];
        this.alphabet.getClass();
        Alphabet.translateString(arrby, (byte)36, (byte)16, this.alphabet.charToByte, (byte)50, (byte)20);
        this.alphabet.getClass();
        Alphabet.translateString(arrby2, (byte)36, (byte)16, this.alphabet.charToByte, (byte)50, (byte)20);
        logger.info("Building conserved file suffix tree ");
        this.alphabet.getClass();
        object = new SuffixTree(arrby, this.byteMultiple, false, 16);
        logger.info("Building background file suffix tree ");
        this.alphabet.getClass();
        SuffixTree suffixTree = new SuffixTree(arrby2, this.byteMultiple, false, 16);
        int[] arrn2 = new int[arrby.length];
        logger.info("Parsing occurrence counts from conserved tree ");
        TrawlerTools.parseCounts(0, arrn2, (SuffixTree)object);
        int[] arrn3 = new int[arrby2.length];
        logger.info("Parsing occurrence counts from background tree ");
        TrawlerTools.parseCounts(0, arrn3, suffixTree);
        int[] arrn4 = new int[100000];
        int[] arrn5 = new int[100000];
        byte[] arrby4 = new byte[10000];
        this.alphabet.getClass();
        int n = arrby3[arrby3.length - 1] == 15 ? arrby3.length - 1 : arrby3.length;
        logger.info("Calling scan function");
        for (int i = 0; i < n; ++i) {
            this.alphabet.getClass();
            TrawlerTools.scan(0, 1, 0, 1, 0, 0, arrn4, arrn5, arrby3, arrby4, this.minimumOccurrences, (SuffixTree)object, suffixTree, arrn2, arrn3, passOut, arrn, this.maximumMismatches, 15, this.maximumMotifSize, i);
        }
        logger.info(" Finished trawler script in (time s) : " + (System.currentTimeMillis() - l) / 1000L);
    }

    public static TrawlerTools.PassOut output(final float f, final float f2, final float[] arrf, final Writer writer, final Function_Int function_Int, final int n) {
        return new TrawlerTools.PassOut(){
            PrintWriter pW;
            int k;
            {
                this.pW = new PrintWriter(writer);
                this.k = 0;
            }

            public final void pro(int n4, int n2, int n3, byte[] arrby) {
                float f3 = TrawlerStatistics.zScore(n4, n2, n3, arrf);
                if (f3 > f && f3 <= f2) {
                    if (this.k >= n) {
                        logger.info("Maximum number of motifs outputted, exiting to avoid breaking the disk");
                        this.pW.close();
                        System.exit(0);
                    }
                    ++this.k;
                    Trawler.print(n4, n2, f3, n3, arrby, function_Int, this.pW);
                }
            }
        };
    }

    public static final void print(int n, int n2, float f, int n3, byte[] arrby, Function_Int function_Int, PrintWriter printWriter) {
        printWriter.write(n + "");
        printWriter.write(9);
        printWriter.write(n2 + "");
        printWriter.write(9);
        printWriter.write(f + "");
        printWriter.write(9);
        for (int i = 0; i < n3; ++i) {
            printWriter.write((char)function_Int.fn(arrby[i]));
        }
        printWriter.write(10);
    }

    public static void main(String[] arrstring) throws IOException {
        int n;
        Trawler trawler = new Trawler();
        InputMunger inputMunger = trawler.setCommandLineArguments(arrstring);
        if (inputMunger == null) {
            System.exit(0);
        }
        String string = inputMunger.watchStrings(CONSERVED_FILE)[0];
        logger.info("Parsing conserved file ");
        byte[] arrby = Alphabet.readInString(string, (byte)36, trawler.searchReverseComplement);
        String string2 = inputMunger.watchStrings(BACKGROUND_FILE)[0];
        logger.info("Parsing background file ");
        byte[] arrby2 = Alphabet.readInString(string2, (byte)36, trawler.searchReverseComplement);
        float[] arrf = new float[trawler.maximumMotifSize + 1];
        Object object = TrawlerStatistics.buckets(arrby, trawler.maximumMotifSize, trawler.alphabet.validChar);
        int[] arrn = TrawlerStatistics.buckets(arrby2, trawler.maximumMotifSize, trawler.alphabet.validChar);
        arrf[0] = Float.NaN;
        for (n = 1; n < arrf.length; ++n) {
            arrf[n] = ((float)object[n] + 1.0f) / (float)(arrn[n] + 1);
            logger.info("For length : " + n + " , conserved count is : " + (int)object[n] + " , background count is " + arrn[n] + " , prob is " + arrf[n]);
        }
        object = new BufferedWriter(new FileWriter(trawler.outputFile));
        if (trawler.estimateMinimumZScore || trawler.estimateMaximumZScore) {
            if (trawler.estimateMinimumZScore && trawler.estimateMaximumZScore) {
                Debug.pl(" Can not estimate both min and max zscores at the same time. ");
                System.exit(0);
            }
            arrn = new float[]{};
            for (n = 0; n < trawler.numberOfZScoreEstimationIterations; ++n) {
                float[] arrf2 = trawler.tailValues(arrby, arrby2, arrf, trawler.estimateMinimumZScore);
                int[] arrn2 = new float[arrn.length + arrf2.length];
                System.arraycopy(arrn, 0, arrn2, 0, arrn.length);
                System.arraycopy(arrf2, 0, arrn2, arrn.length, arrf2.length);
                arrn = arrn2;
            }
            double d = 0.0;
            for (int n2 : arrn) {
                d += (double)n2;
            }
            d /= (double)arrn.length;
            if (trawler.estimateMinimumZScore) {
                logger.info("Minimum z score is estimated to be : " + d);
                logger.info(" Calculated from values : " + IterationTools.join((float[])arrn, " "));
                trawler.minimumZScore = (float)d;
            } else {
                logger.info("Maximum z score is estimated to be : " + d);
                logger.info(" Calculated from values : " + IterationTools.join((float[])arrn, " "));
                trawler.maximumZScore = (float)d;
            }
        }
        if (trawler.runScanFunction) {
            trawler.runTrawler(arrby, arrby2, Trawler.output(trawler.minimumZScore, trawler.maximumZScore, arrf, (Writer)object, trawler.alphabet.byteToChar, trawler.maximumNumberOfMotifsToReport));
        }
        ((Writer)object).close();
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.trawler;

import bp.common.fp.Predicate_Int;
import bp.trawler.Trawler;
import bp.trawler.TrawlerTools;
import java.util.Collections;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.logging.Logger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TrawlerStatistics {
    static final Logger logger = Logger.getLogger(Trawler.class.getName());

    static final float zScore(int n, int n2, int n3, float[] arrf) {
        float f = arrf[n3];
        float f2 = (float)n2 * f;
        float f3 = (float)Math.sqrt(f2 * (1.0f - f));
        return ((float)n - f2) / f3;
    }

    static final int[] buckets(byte[] arrby, int n, Predicate_Int predicate_Int) {
        int n2;
        int[] arrn = new int[n + 1];
        arrn[0] = Integer.MAX_VALUE;
        for (int i = 0; i < arrby.length; i += n2 + 1) {
            for (n2 = i; n2 < arrby.length && predicate_Int.test(arrby[n2]); ++n2) {
            }
            for (int j = 1; j <= (n2 -= i) && j < arrn.length; ++j) {
                int n3 = j;
                arrn[n3] = arrn[n3] + (n2 - j + 1);
            }
        }
        return arrn;
    }

    static final void replaceSequences(byte[] arrby, byte[] arrby2, Predicate_Int predicate_Int) {
        int n = 0;
        for (int i = 0; i < arrby.length; ++i) {
            if (!predicate_Int.test(arrby[i])) continue;
            while (n < arrby2.length && !predicate_Int.test(arrby2[n])) {
                ++n;
            }
            if (n >= arrby2.length) {
                throw new IllegalStateException(" ran out of back ground sequence for shuffling ");
            }
            arrby[i] = arrby2[n++];
        }
    }

    static final void shuffleSequences(byte[] arrby, int n) {
        byte[] arrby2;
        int n2;
        if (n == 0) {
            throw new IllegalStateException();
        }
        LinkedList<byte[]> linkedList = new LinkedList<byte[]>();
        for (n2 = 0; n2 < arrby.length - 1; n2 += arrby2.length) {
            arrby2 = new byte[n2 + n < arrby.length - 1 ? n : arrby.length - 1 - n2];
            System.arraycopy(arrby, n2, arrby2, 0, arrby2.length);
            linkedList.add(arrby2);
        }
        Collections.shuffle(linkedList);
        n2 = 0;
        for (byte[] arrby3 : linkedList) {
            System.arraycopy(arrby3, 0, arrby, n2, arrby3.length);
            n2 += arrby3.length;
        }
    }

    public static TrawlerTools.PassOut getMaxes(final float[] arrf, final SortedSet<Float> sortedSet, final int n) {
        return new TrawlerTools.PassOut(){
            float min;
            {
                float f = this.min = sortedSet.size() != 0 ? ((Float)sortedSet.first()).floatValue() : Float.NEGATIVE_INFINITY;
                if (n == 0) {
                    throw new IllegalStateException(" Can't use so few mins ");
                }
            }

            public final void pro(int n4, int n2, int n3, byte[] arrby) {
                float f = TrawlerStatistics.zScore(n4, n2, n3, arrf);
                if (Float.isNaN(f)) {
                    return;
                }
                if (f > this.min) {
                    if (sortedSet.size() >= n) {
                        sortedSet.remove(sortedSet.first());
                    }
                    sortedSet.add(new Float(f));
                    this.min = ((Float)sortedSet.first()).floatValue();
                } else if (sortedSet.size() < n) {
                    sortedSet.add(new Float(f));
                    this.min = f;
                }
            }
        };
    }

    public static TrawlerTools.PassOut getMins(final float[] arrf, final SortedSet<Float> sortedSet, final int n) {
        return new TrawlerTools.PassOut(){
            float max;
            {
                float f = this.max = sortedSet.size() != 0 ? ((Float)sortedSet.last()).floatValue() : Float.POSITIVE_INFINITY;
                if (n == 0) {
                    throw new IllegalStateException(" Can't use so few mins ");
                }
            }

            public final void pro(int n4, int n2, int n3, byte[] arrby) {
                float f = TrawlerStatistics.zScore(n4, n2, n3, arrf);
                if (Float.isNaN(f)) {
                    return;
                }
                if (f < this.max) {
                    if (sortedSet.size() >= n) {
                        sortedSet.remove(sortedSet.last());
                    }
                    sortedSet.add(new Float(f));
                    this.max = ((Float)sortedSet.last()).floatValue();
                } else if (sortedSet.size() < n) {
                    sortedSet.add(new Float(f));
                    this.max = f;
                }
            }
        };
    }

}

/*
 * Decompiled with CFR 0.148.
 */
package bp.trawler;

import bp.common.ds.SuffixTree;

public final class TrawlerTools {
    static final boolean matches(byte by, byte by2) {
        return (by | by2) == by2;
    }

    static final int scan(int[] arrn, int n, int n2, SuffixTree suffixTree, int n3, byte by) {
        int n4 = n2;
        for (int i = n; i < n2; ++i) {
            int n5 = arrn[i];
            if (suffixTree.isLeaf(n5)) {
                if (!TrawlerTools.matches(suffixTree.string[n5 / 2 + n3], by)) continue;
                arrn[n4++] = n5;
                continue;
            }
            if (n3 < suffixTree.depth(n5)) {
                if (!TrawlerTools.matches(suffixTree.string[suffixTree.headPosition(n5) + n3], by)) continue;
                arrn[n4++] = n5;
                continue;
            }
            int n6 = suffixTree.firstChild(n5);
            do {
                if (suffixTree.isLeaf(n6)) {
                    if (!TrawlerTools.matches(suffixTree.string[n6 / 2 + n3], by)) continue;
                    arrn[n4++] = n6;
                    continue;
                }
                if (!TrawlerTools.matches(suffixTree.string[suffixTree.headPosition(n6) + n3], by)) continue;
                arrn[n4++] = n6;
            } while (suffixTree.isNode(n6 = suffixTree.siblingOrSuffixLink(n6)));
        }
        return n4;
    }

    static final void scan(int n, int n2, int n3, int n4, int n5, int n6, int[] arrn, int[] arrn2, byte[] arrby, byte[] arrby2, int n7, SuffixTree suffixTree, SuffixTree suffixTree2, int[] arrn3, int[] arrn4, PassOut passOut, int[] arrn5, int n8, int n9, int n10) {
        for (int i = 0; i < arrby.length; ++i) {
            TrawlerTools.scan(n, n2, n3, n4, n5, n6, arrn, arrn2, arrby, arrby2, n7, suffixTree, suffixTree2, arrn3, arrn4, passOut, arrn5, n9, n8, n10, i);
        }
    }

    static final void scan(int n, int n2, int n3, int n4, int n5, int n6, int[] arrn, int[] arrn2, byte[] arrby, byte[] arrby2, int n7, SuffixTree suffixTree, SuffixTree suffixTree2, int[] arrn3, int[] arrn4, PassOut passOut, int[] arrn5, int n8, int n9, int n10, int n11) {
        int n12 = n6 + arrn5[n11];
        if (n12 > n8) {
            return;
        }
        byte by = arrby[n11];
        int n13 = TrawlerTools.scan(arrn, n, n2, suffixTree, n5, by);
        int n14 = TrawlerTools.getCount(n2, n13, arrn, arrn3, suffixTree);
        if (n14 >= n7) {
            int n15 = TrawlerTools.scan(arrn2, n3, n4, suffixTree2, n5, by);
            int n16 = TrawlerTools.getCount(n4, n15, arrn2, arrn4, suffixTree2);
            arrby2[n5] = by;
            if (by != n9) {
                passOut.pro(n14, n16, n5 + 1, arrby2);
            }
            if (n5 + 1 < n10) {
                TrawlerTools.scan(n2, n13, n4, n15, n5 + 1, n12, arrn, arrn2, arrby, arrby2, n7, suffixTree, suffixTree2, arrn3, arrn4, passOut, arrn5, n9, n8, n10);
            }
        }
    }

    static final int getCount(int n, int n2, int[] arrn, int[] arrn2, SuffixTree suffixTree) {
        int n3 = 0;
        for (int i = n; i < n2; ++i) {
            n3 += TrawlerTools.getCount(arrn[i], arrn2, suffixTree);
        }
        return n3;
    }

    static final int getCount(int n, int[] arrn, SuffixTree suffixTree) {
        return suffixTree.isLeaf(n) ? 1 : arrn[suffixTree.headPosition(n)];
    }

    static final int parseCounts(int n, int[] arrn, SuffixTree suffixTree) {
        if (suffixTree.isLeaf(n)) {
            return 1;
        }
        int n2 = 0;
        int n3 = suffixTree.firstChild(n);
        while (suffixTree.isNode(n3)) {
            n2 += TrawlerTools.parseCounts(n3, arrn, suffixTree);
            n3 = suffixTree.siblingOrSuffixLink(n3);
        }
        arrn[suffixTree.headPosition((int)n)] = n2;
        return n2;
    }
    static interface PassOut {
        public void pro(int var1, int var2, int var3, byte[] var4);
    }

}

