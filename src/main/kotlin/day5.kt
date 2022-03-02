import java.io.File
import java.lang.Math.abs

fun main(args:Array<String>){
    var file:File = File("E:\\IdeaProjects\\Adv\\Input\\advday5.txt")
    var day5input = file.readLines()

    var i = 0

    var coord : MutableList<List<Int>> = MutableList<List<Int>>(1,{it->List<Int>(1,{it->1})})
    while(i<day5input.size){

        day5input[i].split(" -> ").forEach {
            var temp : List<Int> = it.split(',').filterNot { it.isBlank() }.map { it.toInt() }
            coord.add(temp)
        }
		  i++;
    }

    coord.removeAt(0)

    var vents : Array<Array<Int>> = Array<Array<Int>>(1000,{it->Array<Int>(1000,{it->0})})


    var safe : Int = 0

    var r = 0

    while(r<coord.size){
        if(coord[r][0] == coord[r+1][0] || coord[r][1] == coord[r+1][1]) {
            safe += (linearpoints(vents, coord[r][0], coord[r][1], coord[r + 1][0], coord[r + 1][1]))
        }
        r+=2
    }

    println("Intersecting points count : ${safe}")

    //part two

    var safe2 = 0
    r = 0
    while(r<coord.size){
        var tangent = (abs(coord[r][0] - coord[r+1][0]) == abs(coord[r][1] - coord[r+1][1]))
        //tan45 = 1, tan 90+45 = -1
        if(tangent) {
            safe2 += (diagnolpoints(vents, coord[r][0], coord[r][1], coord[r + 1][0], coord[r + 1][1]))
        }
        r+=2
    }
    println("Intersecting points count with diagnols : ${safe2+safe}")
}

fun linearpoints(vent: Array<Array<Int>>,x1 : Int,y1 : Int,x2 : Int,y2 : Int) :Int{
    var x11:Int = x1
    var x22:Int = x2
    var y11:Int = y1
    var y22:Int = y2
    var safe = 0
    if(x11 == x22 && y11 > y22){
        y11 = y2
        y22 = y1
    }
    else if(y11 == y22 && x11 > x22) {
        x11 = x2
        x22 = x1
    }

    while(x11<=x22 && y11<=y22){
        vent[x11][y11] += 1

        if(vent[x11][y11] == 2)
            safe++

        if(x11 == x22)
            y11++
        else
            x11++
    }

    return safe

}

fun diagnolpoints(vent: Array<Array<Int>>,x1 : Int,y1 : Int,x2 : Int,y2 : Int) :Int{
    var x11:Int = x1
    var x22:Int = x2
    var y11:Int = y1
    var y22:Int = y2
    var safe = 0

    while(x11!=x22 && y11!=y22){
        vent[x11][y11] += 1

        if(vent[x11][y11] == 2)
            safe++

        if(x11 < x22)
            x11++

        else if(x11 > x22)
            x11--

        if(y11 < y22)
            y11++

        else if(y11 > y22)
            y11--
    }

    vent[x22][y22]+= 1
    if(vent[x22][y22] == 2)
        safe++

    return safe

}