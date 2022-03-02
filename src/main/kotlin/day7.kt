import java.io.File
import java.util.*

fun main(args : Array<String>){
    var file : File = File("E:\\IdeaProjects\\Adv\\Input\\advday7.txt")
    var day7input = file.readLines()[0].split(',').filterNot { it.isBlank() }.map { it.toInt()}

    var max = maxElem(day7input)

    var crabs = Array<Int>(max+1,{0})
    day7input.forEach {
        crabs[it]++
    }
    //optimal substructure for calulating fuel cost left or right ways
    /*
    https://www.reddit.com/r/adventofcode/comments/rar7ty/2021_day_7_solutions/hnkq3e7/
    part 1
        --------------------->
        n denotes position
        crab(i) denotes no of crabs at that position
        rightcost(0) = 0
        rightcost(n) = rightcost(n-1)+costtomoveto(n)

        costtomove(n) = crab(n-1)+...+crab(0) as for n step a crab will be added n times in fuel co
        <--------------------
        leftcost(last) = 0
        leftcost(n) = leftcost(n+1)+costtomoveto(n)

        costtomove(n) = crab(n+1)+...+crab(last)

        fuelcost for each crab position i = leftcost(i)+rightcost(i)
        take out the min fuelcost(i)

    part 2 costtomove differs
        costtomove2(n) = costtomove2(n-1)+costtomove(n-1)
riority
    */

    var rightcost = Array<Int>(max+1,{0})
    var leftcost = Array<Int>(max+1,{0})
    var totalcost = Array<Int>(max+1,{0})
    var costtomove = 0

    for(i in 1..max){

        costtomove += crabs[i-1]
        rightcost[i] = rightcost[i-1]+costtomove
        totalcost[i] = rightcost[i]
    }

    costtomove = 0
    for(i in max-1 downTo 0){
        costtomove += crabs[i+1]
        leftcost[i] = leftcost[i+1]+costtomove
        totalcost[i] += leftcost[i]
    }
    var mincost = totalcost.minOrNull()
    var minpos = totalcost.binarySearch(mincost)
    println("Optimal crab position : ${minpos} and Fuel required : ${mincost}")

    //part two
    var rightcost2 = Array<Long>(max+1,{0})
    var leftcost2 = Array<Long>(max+1,{0})
    var totalcost2 = Array<Long>(max+1,{0})
    var crabpassed = 0
    var costtomove2 : Long=0
    for(i in 1..max){
        crabpassed += crabs[i-1]
        costtomove2 += crabpassed
        rightcost2[i] = rightcost2[i-1]+costtomove2
        totalcost2[i] = rightcost2[i]
    }


    costtomove2 = 0
    crabpassed=0
    for(i in max-1 downTo 0){
        crabpassed += crabs[i+1]
        costtomove2 += crabpassed
        leftcost2[i] = leftcost2[i+1]+costtomove2
        totalcost2[i] += leftcost2[i]
    }
    var mincost2 : Long? = totalcost2.minOrNull()
    minpos = totalcost.binarySearch(mincost,0,max+1)
    println("Optimal crab position : ${minpos} and Fuel required : ${mincost2}")



}

fun maxElem(list:List<Int>) : Int{
    var max : Int = 0
    list.forEach {
        if(it>max)
            max=it
    }
    return max
}


