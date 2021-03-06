package org.nd4j.linalg.cpu.nativecpu.blas;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.nd4j.linalg.api.blas.impl.SparseBaseLevel2;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.nativeblas.SparseNd4jBlas;


import static org.bytedeco.javacpp.mkl_rt.*;

/**
 * @author Audrey Loeffel
 */
public class SparseCpuLevel2 extends SparseBaseLevel2 {
    private SparseNd4jBlas sparseNd4jBlas = (SparseNd4jBlas) Nd4j.sparseFactory().blas();
    // Mapping with Sparse Blas calls

    public void scoomv(char transA, int M, DataBuffer values, DataBuffer rowInd, DataBuffer colInd, int nnz, INDArray x, INDArray y){
        mkl_cspblas_scoogemv(
                Character.toString(transA),
                (IntPointer) Nd4j.createBuffer(new int[]{M}).addressPointer(),
                (FloatPointer) values.addressPointer(),
                (IntPointer) rowInd.addressPointer(),
                (IntPointer) colInd.addressPointer(),
                (IntPointer) Nd4j.createBuffer(new int[]{nnz}).addressPointer(),
                (FloatPointer) x.data().addressPointer(),
                (FloatPointer)y.data().addressPointer());
    }
    public void dcoomv(char transA, int M, DataBuffer values, DataBuffer rowInd, DataBuffer colInd, int nnz, INDArray x, INDArray y){
        mkl_cspblas_dcoogemv(
                Character.toString(transA),
                (IntPointer) Nd4j.createBuffer(new int[]{M}).addressPointer(),
                (DoublePointer) values.addressPointer(),
                (IntPointer) rowInd.addressPointer(),
                (IntPointer) colInd.addressPointer(),
                (IntPointer) Nd4j.createBuffer(nnz).addressPointer(),
                (DoublePointer) x.data().addressPointer(),
                (DoublePointer)y.data().addressPointer());
    }
}
