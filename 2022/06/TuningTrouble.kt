import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("./input.txt").inputStream();

    var marker = CharArray(4)
    var starter = CharArray(14)
    var markerCount = 0
    var starterCount = 0

    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { carachters ->
            carachters.forEachIndexed { index, c ->
                marker[index % 4] = c
                starter[index % 14] = c
                if (index >= 4  && markerCount == 0 && marker.toList() == marker.distinct())
                    markerCount = index
                if (index >= 14 && starterCount == 0 && starter.toList() == starter.distinct())
                    starterCount = index
            }
        }
    }
    println("markerCount: " + markerCount)
    println("starterCount: " + starterCount)
}