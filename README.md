# Oh Calculator

A Java calculator for the group algebra of the full octahedral group \( O_h \), with support for Fourier-basis methods, character-table irreps, and explicit \( SU(2) \) and \( SU(3) \) structures inside the algebra.

## Purpose

This project is a research and teaching calculator for working explicitly with finite-group algebra, especially the algebra of the octahedral symmetry group \( O_h \).

The goals of the project include:

- calculations in the group algebra of \( O_h \)
- support for important subgroups such as \( D_4 \), \( S_3 \), and \( S_4 \)
- construction of class sums and central idempotents from the character table
- generalized Fourier-transform methods
- explicit \( SU(2) \) and \( SU(3) \)-type blocks inside the group algebra
- eventual conversion between Fourier basis and standard matrix basis
- applications to cooling calculations such as \( \mathrm{Pauli}[D_4] \)

## Current status

The code currently includes:

- complex arithmetic
- `2x2` and `3x3` complex matrix arithmetic
- a permutation-first definition of the 48 elements of \( O_h \)
- multiplication table generation from canonical permutation labels
- conjugacy-class bookkeeping and class-sum tests
- character-table central idempotents
- Fourier-basis elements
- verified `SU(2)` blocks
- verified `SU(3)` blocks

A substantial amount of testing has been built into the project. The design preference is:

**quiet success, loud failure**

## Design philosophy

This code is written to be readable and heavily commented.

The main design principles are:

- clarity over cleverness
- explicit logic over hidden magic
- correctness before elegance
- strong internal testing
- no graduate student left behind

This is not meant to be an opaque black box. It is meant to be understandable.

## Mathematical conventions

The 48 basis elements of \( O_h \) are indexed in a fixed canonical order:

- first by conjugacy class
- then alphabetically within each class in permutation notation

That canonical basis order is the sovereign definition used throughout the code.

Multiplication is defined from the permutation representation, with inversion handled explicitly.

## Main features

### Group-algebra layer

The code supports direct calculations with elements of the group algebra of \( O_h \).

This includes:

- basis elements
- arbitrary linear combinations
- multiplication
- class-grouped printing
- permutation-label bookkeeping

### Character-table layer

The code builds and tests the central idempotents associated with the irreducible characters of \( O_h \).

These are checked for:

- idempotence
- annihilation between distinct irreps
- correct sum to the identity of the group algebra

### Fourier / block layer

The project includes a Fourier-style basis adapted to the irreducible structure of the algebra.

Inside that basis, the code currently verifies:

- two `SU(2)`-type blocks
- four `SU(3)`-type blocks

These are represented using Pauli-like and Gell-Mann-like generators.

### Matrix layer

The project includes:

- `Matrix2x2`
- `Matrix3x3`

with arithmetic and testing, in preparation for explicit conversion from Fourier basis to standard matrix basis.

## Repository structure

The project is written in Java and developed in Apache NetBeans.

Typical contents include:

- Java source files under `src`
- project metadata
- Fourier-basis support code
- matrix classes
- extensive test methods in the main driver

One reference file used during development is:

- `OhFourierTransform.txt`

## Building and running

This is a Java project intended to be opened in Apache NetBeans.

Typical workflow:

1. Open the project in NetBeans
2. Build the project
3. Run the main class
4. Inspect the test output

The project is designed so that tests run automatically.

## Testing

The project includes tests for:

- complex numbers
- Pauli values
- `2x2` matrix arithmetic
- `3x3` matrix arithmetic
- basis-element bookkeeping
- permutation-label multiplication
- multiplication-table consistency
- associativity spot checks
- class-sum closure
- character-table irreps
- Fourier-basis structural checks
- `SU(2)` block relations
- `SU(3)` block relations

These tests are central to the project and not an afterthought.

## Intended audience

This repository may be useful to:

- students learning finite-group representations
- researchers working with finite-group algebras
- people interested in harmonic analysis on finite groups
- mathematical physicists exploring \( SU(2) \), \( SU(3) \), and related algebraic structure arising from finite symmetry

## License

This project is released under the MIT License.

Please retain the license and attribution information in redistributions.

## Attribution

If you use this repository, please note its source and, where appropriate, cite or link to this repository.

## Development notes

The project has been developed with substantial interactive debugging, design, and documentation assistance from ChatGPT.

## Future work

Planned or likely future steps include:

- conversion between Fourier basis and explicit matrix basis
- construction of matrix-unit bases
- additional subgroup support
- cooling calculations for \( \mathrm{Pauli}[D_4] \)
- further tools for exploring irreducible sectors and physical interpretations

## Author

Carl Brannen
