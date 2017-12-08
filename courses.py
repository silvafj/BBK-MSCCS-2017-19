from datetime import date

class BirkbeckCourse:
    """This class represents a course at Birkbeck."""

    def __init__(self, department, code, title, instructors=None):
        self.department = department
        self.code = code
        self.title = title
        self.students = dict()
        self.instructors = list()

        if instructors is None:
            pass
        elif isinstance(instructors, str):
            self.instructors.append(instructors)
        else:
            self.instructors = list(instructors)

    def mark_attendance(self, day, *students):
        """
        Mark students as present.

        :param date day: When students attended
        :param *students: Sequence of student names attending the course
        """
        if day not in self.students:
            self.students[day] = set()

        self.students[day].update(set(students))

    def is_present(self, day, student):
        """
        Check if student is present in the course.

        :param date day: Day of attendance
        :param str student: Student name
        :return: Return True, if student was found in the course
        :rtype: bool
        """
        return day in self.students and student in self.students[day]

    def __eq__(self, value):
        return self.department == value.department and self.code == value.code

    def __le__(self, value):
        return self.code <= value.code


class BirkbeckCSISCourse(BirkbeckCourse):
    """This class represents a CSIS course at Birkbeck."""

    def __init__(self, department, code, title, recorded=False):
        super().__init__(department, code, title)
        self.is_recorded = recorded


class CourseCatalog:
    """This class provides functions to manage a list of courses."""

    def __init__(self, courses):
        self.courses = courses

    def courses_by_department(self, department_name):
        return [course for course in self.courses if course.department == department_name]

    def courses_by_search_term(self, search_snippet):
        return [course for course in self.courses if search_snippet in course.title]
