students = [
     {'first_name':  'Michael', 'last_name' : 'Jordan'},
     {'first_name' : 'John', 'last_name' : 'Rosales'},
     {'first_name' : 'Mark', 'last_name' : 'Guillen'},
     {'first_name' : 'KB', 'last_name' : 'Tonel'}
]

for x in students:
    print x['first_name'], x['last_name']

users = {
 'Students': [
     {'first_name':  'Michael', 'last_name' : 'Jordan'},
     {'first_name' : 'John', 'last_name' : 'Rosales'},
     {'first_name' : 'Mark', 'last_name' : 'Guillen'},
     {'first_name' : 'KB', 'last_name' : 'Tonel'}
  ],
 'Instructors': [
     {'first_name' : 'Michael', 'last_name' : 'Choi'},
     {'first_name' : 'Martin', 'last_name' : 'Puryear'}
  ]
 }
print
print 'Students'
students = users['Students']
i = 1
for x in students:
    print i, x['first_name'], x['last_name'], len(x['first_name']) + len(x['last_name'])
    i += 1
print
print 'Instructors'
instructors = users['Instructors']
i = 1
for x in instructors:
    print i, x['first_name'], x['last_name'], len(x['first_name']) + len(x['last_name'])
    i += 1