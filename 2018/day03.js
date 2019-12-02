'use strict'
const fs = require('fs');

// Part 1
// ======

/**
  #ID @ X,Y: WxH

  Example_
  #1 @ 1,3: 4x4
  #2 @ 3,1: 4x4
  #3 @ 5,5: 2x2

  . (dot) is ignored space
  # is coliding claims

  //   01234567
  // 0 ........
  // 1 ...2222.
  // 2 ...2222.
  // 3 .11##22.
  // 4 .11##22.
  // 5 .111133.
  // 6 .111133.
  // 7 ........
*/
const parseClaims = (input) => {
  const claims = []
  
  for (const line of input) {
    const claim  = line.split(/#(\d+) @ (\d+,\d+): (\d+x\d+)/).filter(n=>n) // #1 @ 1,3: 4x4
    // console.log(claim);
    
    const id = claim[0] // Id of the claim
    const xy = claim[1].split(',') // Where the claim starts
    const wh = claim[2].split('x') // the size of the claim
    
    claims.push( {
      id
      , x: parseInt(xy[0])
      , y: parseInt(xy[1])
      , width: parseInt(wh[0])
      , height: parseInt(wh[1])
    })    
  }
  return claims
}

const fillClaim = (claims, fabric) => {
  const unclaimed = '.'
  claims.forEach(claim => {
    for (let h = 0; h < claim.height; h++) { // y
      for (let w = 0; w < claim.width; w++) { // x
        if (fabric[claim.y+h][claim.x+w] != unclaimed) fabric[claim.y+h][claim.x+w] = '#' // colision
        else fabric[claim.y+h][claim.x+w] =  claim.id // claim.id.substr(-1); // trim down so we can draw the map in proper size
      }
    }
  })
  return fabric
}

Array.prototype.flatMap = function(f) { return this.concat.apply([], this.map(f));} //same as map().reduce((acc, x) => acc.concat(x), []) );

const part1 = input => {
  //input = '#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2' // fabricSize=8
  const fabricSize = 1000 // 1000 square inch of fabric

  const claims = parseClaims(input.split('\n'))
  const unclaimed = '.'
  let fabric = [...Array(fabricSize)].map(x => Array(fabricSize).fill(unclaimed))

  fillClaim(claims, fabric) // edits on the ref of fabric

/*
  let mapstring = fabric.map(x=>x.join('')).join('\r\n')
  fs.writeFile("/temp/aoc-tmp-day3.html",'<pre>'+ mapstring+'</pre>', function(err) {
    if(err) return console.log(err);
    console.log("The file was saved!", "/temp/aoc-tmp-day3.html"); // file:///C:/temp/aoc-tmp-day3.html
  }); 
*/
  let squareInchesColiding = fabric.map( x => x.filter(y => y=='#') ).flatMap(x=>x)
  if (squareInchesColiding.length >= 2) return squareInchesColiding.length // = 98005
  // return fabric.map(x=>x.join('')).join('\n') // draw map
}

// Part 2
// ======

const overlap = (a, b) => ((a.x < b.x + b.width) && (a.y < b.y + b.height) && (b.x < a.x + a.width) && (b.y < a.y + a.height));

const part2 = input => {
  // input = '#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2' // fabricSize=8
  // return fabric[222][670] // = 331 // x670, y222  // eyeballed the html map lol :D
  const claims = parseClaims(input.split('\n'))
  let found = {}

  claims.forEach(a => {
    let loneClaim = true;
    claims.forEach(b => {
      if (a == b) {} // do nothing if they are the same
      else if (overlap(a,b)) loneClaim = false;
    });
    if (loneClaim) found = a
  });
  return found // = id: 311
}

module.exports = { part1, part2 }