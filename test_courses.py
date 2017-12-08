from courses import BirkbeckCourse


def test_course_instantiation():
    course = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
    assert course.title == "Principles of Programming I"
    assert course.code == "PoP-I"


def test_course_attendance():
    course = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
    assert not course.is_present("Fernando")
    course.mark_attendance("Fernando", "John", "Jack")
    assert course.is_present("Fernando")
    assert course.is_present("John")


def test_comparison_operators():
    a = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
    b = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
    assert a == b

    c = BirkbeckCourse("CS", "FoC", "Fundamentals of Computing")
    assert a != c
    assert c <= a

def test_instructors():
    course = BirkbeckCourse("CS", "FoC", "Fundamentals of Computing")
    assert len(course.instructors) == 0

    course = BirkbeckCourse("CS", "FoC", "Fundamentals of Computing", "John")
    assert course.instructors == ["John"]

    course = BirkbeckCourse("CS", "FoC", "Fundamentals of Computing", ("John", "Jack"))
    assert course.instructors == ["John", "Jack"]
