// Javascript example of LinkedList with map

var node3 = {
  value: "last",
  next: null
};

var node2 = {
  value: "middle",
  next: node3
};

var node1 = {
  value: "first",
  next: node2
};

// operations

var first = function(node) {
  return node.value;
};

var rest = function(node) {
  return node.next;
};

// Prepend a new node to the beginning of the list
var cons = function(newValue, list) {
  return {
    value: newValue,
    next: list
  };
};

first(node1); // "first"
first(rest(node1); // "middle"
first(rest(rest(node1))); // "last"

first(cons("new first", node1)); // "new first"
first(rest(cons("new first", node1))); //"first"

function map (list, transform) {
  if (list === null) {
    return null;
  } else {
    return cons(transform(first(list)), map(rest(list), transform));
  }
}

first(
  map(node1, function (val) {return val + " mapped!"})
); // "first mapped"

var rest = function(array) {
  var sliced = array.slice(1, array.length);
  if (sliced.length == 0) {
    return null;
  } else {
    return sliced;
  }
}

var cons = function (newValue, array) {
  return [newValue].concat(array);
}

var list = ["Transylvania", "Forks, WA"];
map(list, function (val) { return val + " mapped!"});
