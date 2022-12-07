import java.io.File
import java.io.InputStream

fun main() {

    val inputStream: InputStream =
        File("/Users/sebi/IdeaProjects/KotlinTest/src/main/kotlin/example.txt").inputStream();

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
        directories.get(searchFor)?.forEach { it ->
            if (it.contains("dir ")) {
                val dir = it.substring(it.lastIndexOf(" ")).trim()
                return resolveDir(directories, dir)
            }
        }
        return directories.get(searchFor)!!
    }

    // resolve directories in directories
    var iteratorDirs = directories.iterator()
    while (iteratorDirs.hasNext()) {
        var list = iteratorDirs.next()
        var iteratorList = list.iterator
    }
    directories.forEach { key, list ->
        list.forEach { it ->
            if(it.contains("dir ")) {
                val dir = it.substring(it.lastIndexOf(" ")).trim()
                val listDir = resolveDir(directories, dir)
                listDir?.forEach { innerIt ->
                    list.add(innerIt)
                }
            }
        }
    }

    resolveDir(directories, "/")
    print(directories)

}

