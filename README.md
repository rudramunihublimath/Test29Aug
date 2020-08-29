# Test29Aug
coding test


- H2 Database is used 
- Swagger is used
- Below samples/test cases are tested
- operation used :  POST /api/totalprice findtotalprice

Samples :

1.
---------------------
{
  "orderList": [
    {
      "name": "A",
      "quantity": 1
    },
	{
      "name": "B",
      "quantity": 1
    },
	{	
      "name": "C",
      "quantity": 1
    },
	{
      "name": "D",
      "quantity": 1
    }
  ]
}

output -  110

---------------------
{
  "orderList": [
    {
      "name": "A",
      "quantity": 1
    },
	{
      "name": "B",
      "quantity": 1
    },
	{	
      "name": "C",
      "quantity": 1
    }
	
  ]
}

output - 100
---------------------

{
  "orderList": [
    {
      "name": "A",
      "quantity": 5
    },
	{
      "name": "B",
      "quantity": 5
    },
	{	
      "name": "C",
      "quantity": 1
    }
  ]
}

output - 370
--------------------

{
  "orderList": [
    {
      "name": "A",
      "quantity": 3
    },
	{
      "name": "B",
      "quantity": 5
    },
	{	
      "name": "C",
      "quantity": 1
    },
	{	
      "name": "D",
      "quantity": 1
    }
  ]
}

output - 280

--------------------