// Creating Persons
CREATE (:Person {userId: 1, name: 'Alice'});
CREATE (:Person {userId: 2, name: 'Bob'});
CREATE (:Person {userId: 3, name: 'Charlie'});
CREATE (:Person {userId: 4, name: 'David'});
CREATE (:Person {userId: 5, name: 'Emma'});
CREATE (:Person {userId: 6, name: 'Frank'});
CREATE (:Person {userId: 7, name: 'Grace'});
CREATE (:Person {userId: 8, name: 'Helen'});

// Creating Connections
MATCH (a:Person {userId: 1}), (b:Person {userId: 2})
CREATE (a)-[:CONNECTED_TO]->(b);

MATCH (b:Person {userId: 2}), (c:Person {userId: 3})
CREATE (b)-[:CONNECTED_TO]->(c);

MATCH (c:Person {userId: 3}), (d:Person {userId: 4})
CREATE (c)-[:CONNECTED_TO]->(d);

MATCH (a:Person {userId: 1}), (d:Person {userId: 4})
CREATE (a)-[:CONNECTED_TO]->(d);

MATCH (d:Person {userId: 4}), (e:Person {userId: 5})
CREATE (d)-[:CONNECTED_TO]->(e);

MATCH (e:Person {userId: 5}), (f:Person {userId: 6})
CREATE (e)-[:CONNECTED_TO]->(f);

MATCH (f:Person {userId: 6}), (g:Person {userId: 7})
CREATE (f)-[:CONNECTED_TO]->(g);

MATCH (g:Person {userId: 7}), (h:Person {userId: 8})
CREATE (g)-[:CONNECTED_TO]->(h);

MATCH (h:Person {userId: 8}), (a:Person {userId: 1})
CREATE (h)-[:CONNECTED_TO]->(a);

// Additional Mutual Connections
MATCH (a:Person {userId: 1}), (e:Person {userId: 5})
CREATE (a)-[:CONNECTED_TO]->(e);

MATCH (b:Person {userId: 2}), (f:Person {userId: 6})
CREATE (b)-[:CONNECTED_TO]->(f);
