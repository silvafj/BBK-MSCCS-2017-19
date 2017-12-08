from courses import BirkbeckCourse, CourseCatalog
from datetime import date, datetime, timedelta

def test_course_instantiation():
    course = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
    assert course.title == "Principles of Programming I"
    assert course.code == "PoP-I"


def test_course_attendance():
    course = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
    assert not course.is_present(date.today, "Fernando")

    course.mark_attendance(date.today, "Fernando", "John", "Jack")
    assert course.is_present(date.today, "Fernando")
    assert course.is_present(date.today, "John")

    yesterday = date.fromtimestamp((datetime.now() - timedelta(days=1)).timestamp())
    assert not course.is_present(yesterday, "Fernando")


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

def test_courses_by_department():
    catalog = CourseCatalog([
        BirkbeckCourse("CS", "PoP-I", "Principles of Programming I"),
        BirkbeckCourse("DS", "PoP-I", "Principles of Programming I"),
        BirkbeckCourse("CS", "FoC", "Fundamentals of Computing"),
        BirkbeckCourse("DS", "FoC", "Fundamentals of Computing"),
        BirkbeckCourse("CS", "IS", "Information Systems"),
    ])

    cs_codes = [course.code for course in catalog.courses_by_department("CS")]
    assert cs_codes == ["PoP-I", "FoC", "IS"]
    assert len(catalog.courses_by_search_term("Computing")) == 2
    assert len(catalog.courses_by_search_term("of")) == 4
