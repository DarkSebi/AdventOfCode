import java.io.File
import java.io.InputStream
import kotlin.reflect.typeOf

fun main() {

    val inputStream: InputStream =
        File("./input.txt").inputStream();

    var directories: HashMap<String, ArrayList<String>> = HashMap()
    var cur = ""

    // get inputs
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            if (it.contains("$ cd"))
                cur = it.substring(it.lastIndexOf(" ")).trim()
            else if (it.contains("$ ls")) // ignore
            else if (!directories.containsKey(cur)) {
                val list = ArrayList<String>()
                list.add(it)
                directories.put(cur, list)
            } else {
                directories.get(cur)?.add(it)
            }

        }
    }


    fun resolveDir(directories: HashMap<String, ArrayList<String>>, searchFor: String): ArrayList<String> {
        val list = directories.get(searchFor)
        val listIterator = list!!.iterator()
        while (listIterator.hasNext()) {
            val item = listIterator.next()
            if (item.contains("dir ")) {
                val dir = item.substring(item.lastIndexOf(" ")).trim()
                val listDir = directories.get(dir)
                val innerListIterator = listDir!!.iterator()
                while (innerListIterator.hasNext()) {
                    val innerItem = innerListIterator.next()
                    list.add(innerItem)
                }
                list.remove(item)
                return resolveDir(directories, searchFor)
            }
        }
        return list
    }

    resolveDir(directories, "/")
    print(directories)

}

