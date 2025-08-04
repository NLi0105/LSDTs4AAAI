# filepath: your_project/openfast_utils/utils.py
import os
import subprocess
from openfast_toolbox.io import FASTOutputFile

def run_openfast(test_addr, case_name, openfast_addr):
    """Run OpenFAST for a specific turbine case."""
    case_path = os.path.join(test_addr, case_name)
    os.chdir(case_path)
    fst_file = f"{case_name}.fst"
    command = [openfast_addr, fst_file]
    subprocess.run(command)

def read_openfast_output(test_addr, case_name):
    """Read OpenFAST output file into a DataFrame."""
    output_file_path = os.path.join(test_addr, case_name, f"{case_name}.outb")
    df = FASTOutputFile(output_file_path).toDataFrame()
    return df

def read_weis_output(folder, file_name):
    """Read WEIS output file into a DataFrame."""
    output_file_path = os.path.join(folder, file_name)
    fast_output = FASTOutputFile(output_file_path)
    df = fast_output.toDataFrame()
    return df

def readline_filterComments(f):
    """Read lines from a file, skipping comments."""
    while True:
        line = f.readline().strip()
        if line and not line.startswith('!'):
            return line