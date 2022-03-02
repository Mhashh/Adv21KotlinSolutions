import java.io.File

fun main(args : Array<String>){
    var file = File("E:\\IdeaProjects\\Adv\\Input\\day8.txt")
    var day8input = file.readLines().map { it.split(" | ")[1]}

    var count = 0
    day8input.forEach {
        var tmp=it.split(' ')
        var prnt = ""
        tmp.forEach { eachdigit:String ->
            if(eachdigit.length in arrayOf(2,3,4,7)){
                count++
            }
        }
    }
    println(count)
}