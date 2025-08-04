""" 
- Open and OpenFAST binary file
- Convert it to a pandas dataframe
- Compute damage equivalent load for a given Wohler exponent
"""
import os
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from openfast_toolbox.io import FASTOutputFile
from openfast_toolbox.postpro import equivalent_load

def compute_damage_equivalent_load(fastout_filename, signal_column, wohler_exponent, method='default'):
    """
    Compute damage equivalent load for a given Wohler exponent from an OpenFAST binary file.

    Parameters:
    - fastout_filename (str): Path to the OpenFAST binary file.
    - signal_column (str): Column name of the signal to compute the equivalent load for.
    - wohler_exponent (int): Wohler exponent (slope).
    - method (str): Method to compute the equivalent load ('default' or 'fatpack').

    Returns:
    - Leq (float): Damage equivalent load.
    - df (pd.DataFrame): DataFrame containing the data from the OpenFAST binary file.
    """
    # Read the OpenFAST binary file
    df = FASTOutputFile(fastout_filename).toDataFrame()

    # Compute equivalent load for the specified signal and Wohler slope
    if method == 'fatpack':
        Leq = equivalent_load(df['Time_[s]'], df[signal_column], m=wohler_exponent, method='fatpack')
    else:
        Leq = equivalent_load(df['Time_[s]'], df[signal_column], m=wohler_exponent)

    return Leq, df