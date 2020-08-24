import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.*
import java.net.URL
import java.net.URLConnection

fun main() {
    val getimg = PixivDownloader()
    getimg.GetIMG("82429481")
}

/*
*本app最基本也是最核心的类
*与pixiv网络交互
* 使用了国内pixiv.cat的代理
 */
class PixivDownloader {

    /*
    获取制定pvid的图片
     */
    fun GetIMG(pvid: String, name: String? = "test"): Bitmap {
        val savePath: String = "D:\\image\\"
        var uri: String = "https://pixiv.cat/$pvid.jpg"
        val bitmap = downloadURLIMG(uri)
        return bitmap
    }

    @Throws(Exception::class)
    fun downloadURLIMG(urlString: String?): Bitmap {
        // 构造URL
        val url = URL(urlString)
        // 打开连接
        val con: URLConnection = url.openConnection()
        //设置请求超时为5s
        con.setConnectTimeout(2 * 1000)
        // 输入流
        val `is`: InputStream = con.getInputStream()
        //从流中获取bitmap
        val bitmap: Bitmap = BitmapFactory.decodeStream(`is`)
        // 完毕，关闭所有链接
        `is`.close()
        return bitmap
    }

    fun SaveBitmapToLocal(
        bitmap: Bitmap?,
        filename: String = "test.jpg",
        savePath: String? = "D:\\image\\"
    ) {
        if (bitmap == null) {
            return
        }
        try {
            //创建临时文件
            val file = File(savePath)
            //创建文件写入流
            val fos = FileOutputStream(file.path + "\\" + filename)
            //将目标文件转化为流
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            //写入
            fos.flush()
            Log.i("Connect", "save  file successful")
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}