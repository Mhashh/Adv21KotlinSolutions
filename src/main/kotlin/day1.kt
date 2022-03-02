
import java.io.File

fun main(args: Array<String>) {

    val file : File = File("E:\\IdeaProjects\\Adv\\Input\\advday1.txt")
    var increase:Int = 0;
    var previous:Int=-1;
    file.forEachLine(Charsets.UTF_8,{
        if(it.toInt()>previous) {
            increase++;
        }
        previous = it.toInt();
    })
    println("Increases : ${increase-1}")

    // part two
    val read = file.reader()
    val input = read.readLines()
    var increase2 = 0;
    var currentsum = 0;
    for(i in 0..2){
        currentsum += input[i].toInt()
    }

    var nextthreesum = 0
    for(i in 3..input.lastIndex){

        nextthreesum = currentsum - input[i-3].toInt() + input[i].toInt()
        if(currentsum<nextthreesum)
            increase2++

        currentsum = nextthreesum
    }

    println("Increases : ${increase2}")
}