package com.kevin.androidopenglstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin on 2020/5/20<br/>
 * Blog:http://student9128.top/
 * 公众号：竺小竹
 * Describe:<br/>
 */
public class GLRender implements GLSurfaceView.Renderer {
    Triangle mTriangle;
    Triangle2 mTriangle2;
    Triangle3 mTriangle3;
    Triangle4 mTriangle4;
    private String type;
    //Model View Projection Matrix --模型视图投影矩阵
    private static final float[] mMvpMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private static final float[] mProjectionMatrix = new float[16];
    //视图矩阵 viewmatrx
    private static final float[] mViewMatrix = new float[16];
    //变换矩阵
    private float[] mOpMatrix = new float[16];

    private Bitmap bitmap;
    private Context mContext;

    public GLRender(String type) {
        this.type = type;
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);//背景色红色
        mTriangle = new Triangle();
        mTriangle2 = new Triangle2();
        mTriangle3 = new Triangle3();
        mTriangle4 = new Triangle4();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        if (type == "three"||type == "four") {
            float ration = (float) width / height;
            Matrix.frustumM(mProjectionMatrix, 0, -ration, ration, -1, 1, 3, 7);
            Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1f, 0f);

        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        if (type == "one") {
            mTriangle.draw();
        } else if (type == "two") {
            mTriangle2.draw();
        } else if (type == "three") {
            //旋转30度
//            Matrix.setRotateM(mOpMatrix, 0, 30, 0, 0, -1);
//            Matrix.multiplyMM(mMvpMatrix,0,mViewMatrix,0,mOpMatrix,0);
//            Matrix.multiplyMM(mMvpMatrix, 0, mProjectionMatrix, 0, mMvpMatrix, 0);
            Matrix.multiplyMM(mMvpMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
            mTriangle3.draw(mMvpMatrix);
        } else if (type == "four") {
            Matrix.multiplyMM(mMvpMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
            mTriangle4.draw(mMvpMatrix);
        }
    }
}
