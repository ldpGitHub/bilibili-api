package com.hiczp.bilibili.api

import org.apache.commons.io.IOUtils
import org.apache.commons.io.input.BoundedInputStream
import org.apache.commons.io.input.BoundedReader
import java.io.InputStream
import java.nio.charset.Charset

fun InputStream.readFully(length: Int) = IOUtils.readFully(this, length)!!

/**
 * 以大端模式从流中读取一个 int
 */
fun InputStream.readInt(): Int {
    val byteArray = readFully(4)
    return (byteArray[0].toInt() shl 24) or
            (byteArray[1].toInt() shl 16) or
            (byteArray[2].toInt() shl 8) or
            (byteArray[3].toInt())
}

/**
 * 以大端模式从流中读取一个 unsigned int
 */
@Suppress("EXPERIMENTAL_API_USAGE")
fun InputStream.readUInt() = readInt().toUInt()

fun InputStream.boundedReader(maxCharsFromTargetReader: Int, charset: Charset = Charsets.UTF_8) =
        BoundedReader(reader(charset), maxCharsFromTargetReader)

fun InputStream.bounded(size: Long) = BoundedInputStream(this, size)

@Suppress("EXPERIMENTAL_API_USAGE")
fun InputStream.bounded(size: UInt) = bounded(size.toLong())
