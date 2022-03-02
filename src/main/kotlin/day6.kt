import java.io.File

fun main(args:Array<String>){
    var file : File = File("E:\\IdeaProjects\\Adv\\Input\\advday6.txt")
    var day5input = file.readLines()
    var lanturnFishes  = day5input[0].split(',').filterNot { it.isBlank() }.map{it.toInt()}
    var fishwithAge = Array(9,{0})
    lanturnFishes.forEach{
        fishwithAge[it]++
    }

    println("No. of lanturn fishes after 80 days ${grow(fishwithAge,80)}")

    //part two
    var fishwithAge2 = Array<Long>(9,{0})

    lanturnFishes.forEach{
        fishwithAge2[it]++
    }
    println("No. of lanturn fishes after 80 days ${grow2(fishwithAge2,256)}")

}

fun grow2(current:Array<Long>,days:Int):Long{
    var i = days

    while(i>0){
        var tmp = current[0]
        for(c in 0..5){
            current[c] = current[c+1]
        }
        current[6] = tmp+current[7]

        current[7] = current[8]
        current[8] = tmp
        i--
    }
    var total:Long = 0

    current.forEach {
        total+=it
    }
    return total
}

fun grow(current:Array<Int>,days:Int):Long{
    var i = days

    while(i>0){
        var tmp = current[0]
        for(c in 0..5){
            current[c] = current[c+1]
        }
        current[6] = tmp+current[7]

        current[7] = current[8]
        current[8] = tmp
        i--
    }
    var total:Long = 0

    current.forEach {
        total+=it
    }
    return total
}

