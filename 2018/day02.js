'use strict'

// Part 1
// ======

const part1 = input => {
  var three = []
  var two = []
  var output = input.split('\n').map( x =>  {
    // add x to three if x contains three of the same letter
    // add x to two if x contains two of the same letter
    // (but only do this once, 
    // so for example aabb, only aa counts
    // and cccbbb only counts once
    // however eeeff coutns twice since three and two.
    // 
    /* Example input: krdmtqqjgwjoevnabexyclzsph
       :sort alphabetically
       reveals ee and jj, so 1 point
    */
      /** from example on website (summed on all input lines)
       * three:  1 1 1 = 3
       * two:    1 1 1 1 = 4
       *  * = 12
       * 
      */
    
    const charArray = x.split('').sort() // Sort alphabetically
        
    // finds dups, put them in a list using reduce
    var duplicates = charArray.reduce((acc, el, i, arr) => {
      //if (arr.indexOf(el) !== i && acc.indexOf(el) < 0) acc.push( el );
      if (acc[el] > 0) 
        acc[el] += 1;
      else
        acc[el] = 1;
      return acc;
    }, {}); // []);
    
    /*
    [ 
      {'a': 3},
      {'b': 1},
      {'c': 2},
    ]
    letter.filter >= 3 = add obj to three
    leter.filter >= 2 = add obj to two
  */

    // return charArray
    return duplicates
  });

  // because flatmap doesnt exist in Array ... experimental feature!
  // const flatMap = (f, xs) => xs.map(f).reduce((acc, v) => acc.concat(v), [])
  // Array.prototype.flatMap = function(f) { return flatMap(f,this) }
  Array.prototype.flatMap = function(f) { return this.concat.apply([], this.map(f));}
  
  /* fail, this will find all threes and all twos 
  three = output.map(x => Object.keys(x).filter(y => x[y] == 3)).flatMap( z => z)
  two = output.map(x => Object.keys(x).filter(y => x[y] == 2)).flatMap( z => z)//.reduce((acc, z) => acc.concat(z), []) 
  */
  // Find first three and two in each row
  three = output.map(x => Object.keys(x).find(y => x[y] === 3)).filter(y => y !== undefined)
  two = output.map(x => Object.keys(x).find(y => x[y] === 2)).filter(y => y !== undefined)

//////
  // return { i: input.split('\n')[0] , o: output[0] }
  // return output
  // return three
  // return two
  // return [ three, two]
  // return [three.length, two.length] // [ 24, 248 ] = 5952 (Correct answer)  // FAIL = All threes and twos == [ 24, 630 ] = 15120 (wrong, too big number)
  // return three.length * two.length
  // return ''
//////

  // Trimmed down into 4 lines of code:
  const _output = input.split('\n').map( x => x.split('').sort().reduce((acc, el) => { acc[el] = acc[el] + 1 || 1; return acc; }, {}));
  const _three = _output.map(x => Object.keys(x).find(y => x[y] === 3)).filter(y => y !== undefined);
  const _two = _output.map(x => Object.keys(x).find(y => x[y] === 2)).filter(y => y !== undefined);
  return _three.length * _two.length;
}

// Part 2
// ======

const part2 = input => {
  // Array.prototype.toJson = JSON.stringify.bind(this)
  // var bound = JSON.stringify.bind(this); 
  // Array.prototype.asJson = JSON.stringify.bind(this, arguments); 
  /*
    - Find the input that differs by only 1 letter (remove that letter), across all rows.
    eg 
      abcdef
      qwerty
      abcjef
    = abc_ef because d and j is the difference between abcdef and abcjef
  */
 const output = input.split('\n')
 /*for (let i = 0; i < output.length; i++) {
   const prev = output[i].split('');
   for (let j = 0; j < output.length; j++) {
     const elm = output[j].split('');
     const intersection = elm.filter(x => prev.includes(x));
     let difference = elm.filter(x => !prev.includes(x)).concat(prev.filter(x => !elm.includes(x)));
     
     console.log(intersection.join(''),  JSON.stringify(difference), '=>', output[j], '+', output[i], '>', j, i); 
     
     if (difference.length == 1) return
    }
  }*/
  
  // const intersect = (a, b) => [...a].filter(Set.prototype.has, new Set(b));
  /*
  let a = new Set([1,2,3]);
  let b = new Set([4,3,2]);
  let difference = new Set([...a].filter(x => !b.has(x)));
  */
  /*
  let a = 'abcdef1A'
  let b = 'abcdof2b'
  let intersection_ = [...a].filter((x,i) => b[i] == x); // assumption, strings are of equal length
  console.log( intersection_ )
  */

  const intersect = (a, b) => [...a].filter((x,i) => b[i] === x); // assumption, strings are of equal length
  // const difference = (a, b) => [...a].filter((x,i) => b[i] !== x); // assumption, strings are of equal length
  var found = []
  for (let i = 0; i < output.length; i++) {
    const prev =  output[i]//new Set( output[i].split('') );
    
    for (let j = 0; j < output.length; j++) {
      const elm = output[j]//new Set( output[j].split('') );
      
      console.log([...prev].filter( (x,i) => elm[i] !== x).join('')  );
      

      ///////////////////
      var diff = 0
      // loop thu the strings, they are the same length
      for (let k = 0; k < output[i].length; k++) {
        const element = prev[k];
        const next = elm[k];
        
        if (element == next) continue; // identical string
        if (element !== next) diff++
      }

      if (diff == 1) {
        found.push( { elm, prev } )
        break
      }
      ///////////////////

      //let difference = new Set( [...elm].filter(x => !prev.has(x)) );
      //let intersection = intersect(elm, prev);
      //console.log(intersection.join(''), difference, '=>', output[j], '+', output[i], '>', j, i); 
      //if (difference.size == 1) return 
    }
    if (found.length == 1) break
  }
  // krdmtuqjgwfoevnabox p glzjph
  // krdmtuqjgwfoevnabox y glzjph
  // krdmtuqjgwfoevnabox   glzjph
  let intersection  = intersect(found[0].elm, found[0].prev).join('');
  return intersection
}

module.exports = { part1, part2 }
// https://stackoverflow.com/questions/1187518/how-to-get-the-difference-between-two-arrays-in-javascript
// npx advent init 2
// npx advent run 2 2 + -s 'session=53616c7465645f5fdd8dbd94e31e18e4e880d2d477ae04519b5de79bb611be179bed1445562a7ecadd9fe20c14503565'
// npx advent run <day> <part>
// npx advent init <day>
