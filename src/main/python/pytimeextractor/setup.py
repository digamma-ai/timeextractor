from setuptools import setup

def readme():
    with open('README.rst') as f:
        return f.read()

setup(name='pytimeextractor',
      version='0.1.1',
      description='Time Extractor NLP project - locate dates and times in text documents',
      long_description=readme(),
      keywords='NLP text extraction time date',
      url='https://github.com/digamma-ai/timeextractor',
      author='Digamma.ai',
      author_email='info@digamma.ai',
      license='MIT',
      install_requires=[
          'pyjnius==1.1.1',
      ],
      packages=['pytimeextractor'],
      include_package_data=True,
      test_suite='nose.collector',
      tests_require=['nose'],
      zip_safe=False)
