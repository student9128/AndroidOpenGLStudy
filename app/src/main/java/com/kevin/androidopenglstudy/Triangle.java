package com.kevin.androidopenglstudy;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.TreeMap;

/**
 * Created by Kevin on 2020/5/20<br/>
 * Blog:http://student9128.top/
 * 公众号：竺小竹
 * Describe:<br/>
 */
public class Triangle {
    private FloatBuffer vertexBuffer;
    private final String vertexShaderCode = "attribute vec4 vPosition;" +
            "void main(){" +
            " gl_Position=vPosition;" +
            "}";
    private final String fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main(){" +
            " gl_FragColor=vColor;" + "}";
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private final int vertexCount = sCoo.length / COORDS_PER_VERTEX;//顶点个数
    private final int vertexStride = COORDS_PER_VERTEX * 4;
    static final int COORDS_PER_VERTEX = 3;
    static float sCoo[] = {
            0.0f, 0.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f
    };
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};
    private int mColorHandle1;

    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(sCoo.length * 4);//每个浮点数：坐标个数*4
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();//从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(sCoo);//将坐标添加到FloatBuffer
        vertexBuffer.position(0);
        int vertexShader = GLRender.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GLRender.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);

        GLES20.glDeleteShader(vertexShader);
        GLES20.glDeleteShader(fragmentShader);

    }

    public void draw() {
        GLES20.glUseProgram(mProgram);
        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启动三角形顶点句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        // 获取片元着色器的vColor成员的句柄
        this.mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(this.mColorHandle, 1, color, 0);//为三角形设置颜色

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //禁用顶点数组
        //禁用index指定的通用顶点属性值
        //默认情况下，禁用所有客户端功能，包括所有通用顶点属性数组
        //如果弃用，将访问通用顶点属性数组中的值，并在调用顶点数组命令(如glDrawArrays或glDrawElements)时用于呈现
        GLES20.glDisableVertexAttribArray(mPositionHandle);//禁用顶点数组
    }
}
