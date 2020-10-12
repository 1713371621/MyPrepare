package com.example.myprepare.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myprepare.R
import java.io.*
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket

class IOActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "IOActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_o)

//        io1()
//        io2()
//        io3()
        io4()
    }

    fun io1() {
        var outputStream: OutputStream? = null
        var bufferedOutputStream: BufferedOutputStream? = null
        try {
            outputStream = FileOutputStream("");
            bufferedOutputStream = BufferedOutputStream(outputStream)
            bufferedOutputStream.write("qwe".toByteArray())
            //... bufferedOutputStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                outputStream?.close()
                bufferedOutputStream?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
    }

    fun io2() {
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null
        try {
            inputStream = FileInputStream("file_old")
            outputStream = FileOutputStream("file_new")
            val byteArray = ByteArray(4096)

            var read: Int

            while (inputStream.read(byteArray).also { read = it } != -1) {
                outputStream.write(byteArray, 0, read)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                outputStream?.close()
                inputStream?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
    }

    fun io3() {

        Thread {
            var socket: Socket? = null
            var writer: BufferedWriter? = null
            var reader: BufferedReader? = null
            try {
                socket = Socket("hencoder.com", 80)

                writer = BufferedWriter(java.io.OutputStreamWriter(socket.getOutputStream()))
                reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                writer.write(
                    "GET / HTTP/1.2\n" +
                            "Host: www.example.com\n\n"
                )

                writer.flush()
                var message: String?
                while (reader.readLine().also { message = it } != null) {
                    Log.d(TAG, message ?: "message is null")
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            } finally {
                try {
                    socket?.close()
                    writer?.close()
                    reader?.close()
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
            }
        }.start()
    }

    fun io4() {
        Thread {
            var serverSocket: ServerSocket? = null
            var socket: Socket? = null
            var writer: BufferedWriter? = null
            var reader: BufferedReader? = null
            try {
                serverSocket = ServerSocket(8080)
                socket = serverSocket.accept()

                writer = BufferedWriter(java.io.OutputStreamWriter(socket.getOutputStream()))
                reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                writer.write("HTTP/1.1 200 OK\n" +
                        "Date: Sat, 31 Dec 2005 23:59:59 GMT\n" +
                        "Content-Type: text/html;charset=UTF-8\n" +
                        "Content-Length: 122\n" +
                        "\n" +
                        "＜html＞\n" +
                        "＜head＞\n" +
                        "＜title＞Wrox Homepage＜/title＞\n" +
                        "＜/head＞\n" +
                        "＜body＞\n" +
                        "＜!-- body goes here --＞\n" +
                        "＜/body＞\n" +
                        "＜/html＞\n\n")
                writer.flush()
            } catch (exception: Exception) {
                exception.printStackTrace()
            } finally {
                try {
                    serverSocket?.close()
                    socket?.close()
                    writer?.close()
                    reader?.close()
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
            }
        }.start()
    }
}