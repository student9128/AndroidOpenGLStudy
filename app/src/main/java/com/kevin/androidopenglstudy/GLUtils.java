package com.kevin.androidopenglstudy;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Kevin on 2020/5/21<br/>
 * Blog:http://student9128.top/
 * 公众号：竺小竹
 * Describe:<br/>
 * 工具类
 */
public class GLUtils {
    public static int loadShaderAssets(Context context, int type, String name) {
        String result = null;
        try {
            InputStream inputStream = context.getAssets().open(name);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = inputStream.read()) != -1) {
                baos.write(ch);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            inputStream.close();
            result = new String(bytes);
            result = result.replaceAll("\\r\\n", "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadShader(type, result);
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);//创建着色器
        if (shader == 0) {//加载失败
            return 0;
        }
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return checkCompile(type, shader);
    }

    private static int checkCompile(int type, int shader) {
        int[] compiled = new int[1];//存放编译成功shader数量的数组
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e("GLES20_COMPILE_ERROR", "Could not compile shader " + type + ":" + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
        }
        return shader;
    }

    /**
     * float数组缓冲数据
     *
     * @param vertexs 顶点
     * @return 获取浮点形缓冲数据
     */
    public static FloatBuffer getFloatBuffer(float[] vertexs) {
        FloatBuffer buffer;
        ///每个浮点数:坐标个数* 4字节
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexs.length * 4);
        //使用本机硬件设备的字节顺序
        qbb.order(ByteOrder.nativeOrder());
        // 从字节缓冲区创建浮点缓冲区
        buffer = qbb.asFloatBuffer();
        // 将坐标添加到FloatBuffer
        buffer.put(vertexs);
        //设置缓冲区以读取第一个坐标
        buffer.position(0);
        return buffer;
    }

    /**
     * short数组缓冲数据
     *
     * @param vertexs short 数组
     * @return 获取short缓冲数据
     */
    public static ShortBuffer getShortBuffer(short[] vertexs) {
        ShortBuffer buffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexs.length * 2);
        qbb.order(ByteOrder.nativeOrder());
        buffer = qbb.asShortBuffer();
        buffer.put(vertexs);
        buffer.position(0);
        return buffer;
    }

}
