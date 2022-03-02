
import java.io.File

fun main(args: Array<String>) {
    val file : File = File("E:\\IdeaProjects\\Adv\\Input\\advday2.txt")
    var x:Int = 0;
    var y:Int = 0;
    file.forEachLine(Charsets.UTF_8,{
        var cmd : List<String> = it.split(" ");

        if(cmd[0] == "up") {
            y -= cmd[1].toInt()
        }
        else if(cmd[0] == "down") {
            y += cmd[1].toInt()
        }
        else{
            x +=cmd[1].toInt()
        }

    })

    println("Horizantal distance : ${x} Depth : ${y} multiple ${x*y}")

    //Part two
    x = 0;
    y = 0;
    var aim:Int = 0;

    file.forEachLine(Charsets.UTF_8,{
        var cmd : List<String> = it.split(" ");

        if(cmd[0] == "up") {
            aim -= cmd[1].toInt()
        }
        else if(cmd[0] == "down") {
            aim += cmd[1].toInt()
        }
        else{
            x +=cmd[1].toInt()
            y = y+(aim*(cmd[1].toInt()))
        }

    })
    println("Aim adjustment Horizantal distance : ${x} Depth : ${y} product ${x*y}")
}