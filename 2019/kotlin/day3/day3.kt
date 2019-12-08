package aoc.day.three

import java.io.File
import kotlin.random.Random
import java.awt.*
import java.awt.geom.*
import javax.swing.*

data class WirePosition(
    var x: Int = 0,
    var y: Int = 0,
    var walked: Int = 0 )
data class WireData(
    val direction: String,
    val steps: Int )
data class Wire(
    var actions: List<WireData>,
    var path: List<WirePosition> = listOf(),
    var position: WirePosition = WirePosition() )


fun manhattan(ax: Int, ay: Int, bx: Int, by: Int): Int {
    return Math.abs(ax - bx) + Math.abs(ay - by)
}

fun WireIntersects(a: Wire, b: Wire): List<WirePosition> {
    // Get intersection of wires and calculate Manhattan distance of each and get the minimum distance from origin
    val apath = a.path.map { WirePosition(it.x, it.y) } // Remove Walked property,
    val bpath = b.path.map { WirePosition(it.x, it.y) } // In order to be able to use intersect
    return apath.intersect(bpath).toList()
}

fun WireSteps(a: Wire, b: Wire): Int? {
    var totSteps = listOf<Int>()
    var intersects = WireIntersects(a, b)
    for (cross in intersects) {
        var w1steps = 0
        var w2steps = 0
        for (pos in a.path) {
            if (pos.x == cross.x && pos.y==cross.y) {
                w1steps = pos.walked
            } 
        }
        for (pos in b.path) {
            if (pos.x == cross.x && pos.y==cross.y) {
                w2steps = pos.walked
            } 
        }
        totSteps += ( w1steps + w2steps )
    }
    // println("totSteps: ${totSteps.min()}")
    return totSteps.min()
}
fun WireDistance(a: Wire, b: Wire): Int? {
    var intersects = WireIntersects(a, b)
    println("${intersects.size} intersection found!")
    val dist = intersects.map {
        println("intersection: (${it.x}, ${it.y})")
        manhattan(0, 0, it.x, it.y) 
    }
    return dist.min()
}

fun WalkWire(w: Wire): Wire {
    val wire = w.copy()
    wire.actions.forEach { action ->
        (0..action.steps-1).forEach {
            when (action.direction) {
                "R" -> wire.position.x += 1
                "L" -> wire.position.x -= 1
                "U" -> wire.position.y -= 1
                "D" -> wire.position.y += 1
            }
            wire.position.walked += 1
            wire.path += wire.position.copy()
        }
    }
    return wire
}

fun main(args: Array<String>) {
    val lineList = File("./input_2019-3.txt").readLines().map { it.split(",") }
    var wire1 = lineList.get(0) // distance: 273
    var wire2 = lineList.get(1) // steps: 15622
    
    // val wire1 = listOf("R8","U5","L5","D3")
    // val wire2 = listOf("U7","R6","D4","L4") //  distance: 6, steps: 30
    // val wire1 = listOf("R75","D30","R83","U83","L12","D49","R71","U7","L72")
    // val wire2 = listOf("U62","R66","U55","R34","D71","R55","D58","R83") // distance: 159, steps: 610
    // val wire1 = listOf("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51")
    // val wire2 = listOf("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7") // distance: 135, steps: 410
    
    // Parse raw data into a data structure
    var wires = listOf(wire1, wire2)
    .map { wire ->
        Wire( wire.map { action -> 
            WireData( action.take(1) , action.drop(1).toInt() )
        })
    }

    // Run
    val walkedwires = wires.map { 
        WalkWire(it)
    }

    println("Wire Distance:" + WireDistance(walkedwires[0],walkedwires[1]))
    println("Wire Steos:" + WireSteps(walkedwires[0], walkedwires[1]))

    // Open gui window for drawing
    // EventQueue.invokeLater( {
    //     val frame = Window("Advent Of Code 2019 -  Day 3")
    //     frame.setWires( walkedwires.toMutableList() )
    //     frame.isVisible = true
    // })
}

// Create a window for the Canvas
class Window(title: String) : JFrame() {
    var wires: ArrayList<Wire>
    var canvas: MyCanvas

    init {
        wires = arrayListOf<Wire>()
        setTitle(title)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        // setSize(800, 800)
        setSize(400, 400)
        setLocationRelativeTo(null)
        canvas = MyCanvas()
        add(canvas)
    }

    fun setWires(wireList: MutableList<Wire>) {
        wires.clear()
        wires.addAll(wireList)
        canvas.wires = wires
    }
}

class MyCanvas(): Canvas() {
    var wires: ArrayList<Wire> = arrayListOf<Wire>()

    fun paintWires(gfx: Graphics) {
        wires.forEach { wire ->
            gfx.color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            wire.path.forEach { p ->
                gfx.drawLine(p.x, p.y, p.x, p.y)
                // gfx.drawString("${p.x.toInt()},${p.y.toInt()}", p.x, p.y)
            }
        }
    }

    fun painIntersections(gfx: Graphics) {
        var s = 1f
        gfx.color = Color.WHITE
        gfx.drawString("0, 0", (0/s).toInt(), (0/s).toInt()) // Draw Origin Position
        WireIntersects(wires[0], wires[1]).forEach { p ->
            gfx.color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            gfx.drawString("${p.x.toInt()},${p.y.toInt()}", (p.x/s).toInt(), (p.y/s).toInt())
        }
    }

    override fun paint(gfx: Graphics) {
        gfx.color = Color.BLACK
        gfx.fillRect(0,0,400,400)
        gfx.translate(100,270)
        paintWires(gfx)
        painIntersections(gfx)
    }
}