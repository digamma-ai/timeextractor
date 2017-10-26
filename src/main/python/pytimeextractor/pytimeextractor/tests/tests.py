from unittest import TestCase

from pytimeextractor import ExtractionService


class CompositeDateYear(TestCase):
    def compositeDateTimeInterval(self):
        text = "25th of May 2014"
        rez = ExtractionService.extract(text=text)
        self.assertTrue(rez[0]['temporalExpression']=="25th of May 2014")
