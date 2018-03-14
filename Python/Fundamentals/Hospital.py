class Patient(object):
    def __init__(self, id, name, allgeries, bed_num=None):
        self._id = id
        self._name = name
        self._allergies = allgeries
        self._bed_num = bed_num

class Hospital(object):
    def __init__(self, hosp_name, cap, patients=[]):
        self._patients = patients
        self._hosp_name = hosp_name
        self._cap = cap
    def admit(self, patient):
        if len(self._patients) < self._cap:
            self._patients.append(patient)
            print "admitted"
        else:
            print "at capacity"
        return self
    def discharge(self, id):
        for p in self._patients:
            if p._id == id:
                self._patients.remove(p)
                p._bed_num = None
        return self

h = Hospital("st mary's", 100, [Patient(1,"a",[],10),Patient(2,"b",[],11),Patient(3,"c",[],12)])
h.admit(Patient(4,"d",[],13))
h.discharge(2)
for p in h._patients:
    print p._name, p._bed_num