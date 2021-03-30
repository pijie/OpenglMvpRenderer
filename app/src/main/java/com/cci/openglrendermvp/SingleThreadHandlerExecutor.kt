package com.cci.openglrendermvp

import android.os.Handler
import android.os.HandlerThread
import java.util.concurrent.Executor
import java.util.concurrent.RejectedExecutionException

// 自定义opengl线程
class SingleThreadHandlerExecutor constructor(
    private val threadName: String,
    priority: Int
) : Executor {
    private val mHandThread = HandlerThread(threadName, priority)
    private val mHandler: Handler

    init {
        mHandThread.start()
        mHandler = Handler(mHandThread.looper)
    }

    fun getHandler(): Handler {
        return mHandler
    }

    override fun execute(command: Runnable) {
        if (!mHandler.post(command)) {
            throw RejectedExecutionException("$threadName is shutting down.")
        }
    }

    fun shutdown():Boolean{
        return  mHandThread.quitSafely()
    }
}