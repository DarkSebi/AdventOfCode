import java.io.File
import java.io.InputStream

@ExperimentalStdlibApi
fun main() {

    val inputStream: InputStream =
        File("/Users/sebi/IdeaProjects/KotlinTest/src/main/kotlin/input.txt").inputStream();

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
//
//    val depth = DeepRecursiveFunction<HashMap<String, ArrayList<String>>, String> { t ->
//        val list = t.get("searchFor")
//        val listIterator = list!!.iterator()
//        while (listIterator.hasNext()) {
//            val item = listIterator.next()
//            if (item.contains("dir ")) {
//                val dir = item.substring(item.lastIndexOf(" ")).trim()
//                val listDir = resolveDir(t, dir)//directories.get(dir)!!
//                val innerListIterator = listDir.iterator()
//                while (innerListIterator.hasNext()) {
//                    val innerItem = innerListIterator.next()
//                    list.add(innerItem)
//                }
//                list.remove(item)
//                return resolveDir(t, searchFor)
//            }
//        }
//        return list
//    }

    fun resolveDirT(
        directories: HashMap<String, ArrayList<String>>,
        searchFor: String,
        currentList: ArrayList<String>?
    ): ArrayList<String>? {
        var containsDir = false

        val curList = currentList!!.iterator()
        while (curList.hasNext()) {
            val list = curList.next()
            if (list.contains("dir ")) {
                containsDir = true
            }
        }

        if (!containsDir)
            return currentList

        val curDir = directories.get(searchFor)!!
        val curDirIterator = curDir.iterator()
        while (curDirIterator.hasNext()) {
            val list = curDirIterator.next()
            if (!list.contains("dir ")) {
                continue
            } else {
                val dir = list.substring(list.lastIndexOf(" ")).trim()
                val listDir = resolveDirT(directories, dir, curDir)
                val innerListIterator = listDir!!.iterator()
                while (innerListIterator.hasNext()) {
                    val innerItem = innerListIterator.next()
                    curDir.add(innerItem)
                }
                curDir.remove(list)
            }
        }
        return resolveDirT(directories, searchFor, directories.get(searchFor))
    }

    fun resolveDir(directories: HashMap<String, ArrayList<String>>, searchFor: String): ArrayList<String> {
        val list = directories.get(searchFor)
        val listIterator = list!!.iterator()
        while (listIterator.hasNext()) {
            val item = listIterator.next()
            if (item.contains("dir ")) {
                val dir = item.substring(item.lastIndexOf(" ")).trim()
                val listDir = directories.get(dir)!!
                val innerListIterator = listDir.iterator()
                while (innerListIterator.hasNext()) {
                    val innerItem = innerListIterator.next()
                    list.add(innerItem)
                }
                list.remove(item)
            } else return list
        }
        return resolveDir(directories, searchFor)
    }

//    directories.forEach { t, u ->
//        if (u.contains("dir ")) {
//            u.forEach {
//                if (it.contains("dir ")) {
//                    val dir = it.substring(it.lastIndexOf(" ")).trim()
//                    resolveDir(directories, dir)
//                }
//
//            }
//
//        }

//    var dirIterator = directories.iterator()
//    while(dirIterator.hasNext()) {
//        var directory = dirIterator.next()
//        var listIterator = directory.value.iterator()
//        while(listIterator.hasNext()) {
//            var item = listIterator.next()
//            var dir = item.substring(item.lastIndexOf(" ")).trim()
//            if(item.contains("dir ")) {
//                resolveDir(directories, dir)
//            }
//        }
//    }

    resolveDir(directories, "/")
    println(directories)

//    println(directories)

//    var directorySize: ArrayList<Int> = ArrayList()
//
//    val dirsIterator = directories.iterator()
//    while (dirsIterator.hasNext()) {
//        val lists = dirsIterator.next()
//        val list = lists.value
//        val listsIterator = list.iterator()
//        var currentSize = 0
//        while(listsIterator.hasNext()) {
//            val item = listsIterator.next() // item in key
//            currentSize += Integer.parseInt(item.substring(0, item.indexOf(" ")))
//        }
//        directorySize.add(currentSize)
//    }
//
//    directorySize.removeIf {
//        it > 100000
//    }
//    print("Q1: total size of directories with at most 100.000: " + directorySize.sum())


}

