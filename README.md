# Building-Design

A Java-based application for modeling and managing building blueprints with floor plans and unit specifications. This project provides an object-oriented approach to designing multi-floor buildings with customizable units and space management.

## Features

- **Unit Management**: Create and manage building units with customizable functions (bedrooms, offices, kitchens, etc.) and dimensions
- **Measurement Toggle**: Switch between feet and meters for unit dimensions
- **Floor Planning**: Design floors with maximum capacity constraints and unit allocation
- **Space Validation**: Automatic validation to prevent exceeding floor capacity when adding units
- **Blueprint Creation**: Organize multiple floor plans into comprehensive building blueprints
- **Composition Preservation**: Deep copying ensures data integrity when retrieving floor plans

## Project Structure

```
Building_Design_Problem/
├── src/
│   ├── model/
│   │   ├── Blueprint.java                    # Building blueprint with multiple floors
│   │   ├── Floor.java                        # Individual floor with units
│   │   ├── Unit.java                         # Building unit (room) with dimensions
│   │   └── InsufficientFloorSpaceException.java  # Custom exception
│   └── junit_tests/
│       └── StarterTests.java                 # JUnit test suite
└── bin/                                      # Compiled class files
```

## Core Classes

### Unit
Represents a building unit (room) with:
- Function/purpose (e.g., "Master Bedroom", "Office", "Kitchen")
- Dimensions (width × length in feet)
- Area calculations in both square feet and square meters
- Measurement mode toggle (feet ↔ meters)

### Floor
Represents a building floor with:
- Maximum capacity in square feet
- Collection of units (up to 20 units per floor)
- Space utilization tracking
- Exception handling for capacity overflow

### Blueprint
Represents a complete building design with:
- Configurable number of floors
- Floor plan management
- Completion percentage tracking
- Deep copy support for floor plans

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- JUnit 4 for running tests

### Compilation

Navigate to the `Building_Design_Problem` directory and compile the Java files:

```bash
cd Building_Design_Problem
javac -d bin src/model/*.java src/junit_tests/*.java
```

### Running Tests

To run the JUnit test suite:

```bash
cd Building_Design_Problem
java -cp bin:path/to/junit-4.jar:path/to/hamcrest-core.jar org.junit.runner.JUnitCore junit_tests.StarterTests
```

## Usage Examples

### Creating a Unit

```java
// Create a unit with function and dimensions (width × length in feet)
Unit masterBedroom = new Unit("Master Bedroom", 14, 9);
// Output: A unit of 126 square feet (14' wide and 9' long) functioning as Master Bedroom

// Toggle to metric measurements
masterBedroom.toggleMeasurement();
// Output: A unit of 11.71 square meters (4.27 m wide and 2.74 m long) functioning as Master Bedroom
```

### Creating a Floor Plan

```java
// Create a floor with 500 sq ft capacity
Floor floor = new Floor(500);

try {
    floor.addUnit("Master Bedroom", 18, 8);  // 144 sq ft
    floor.addUnit("Office", 18, 7);          // 126 sq ft
    // Total: 270 sq ft used, 230 sq ft remaining
} catch (InsufficientFloorSpaceException e) {
    System.out.println("Error: " + e.getMessage());
}
```

### Creating a Blueprint

```java
// Create a blueprint for a 7-floor building
Blueprint blueprint = new Blueprint(7);

Floor floor1 = new Floor(500);
Floor floor2 = new Floor(500);

// Add units to floors (with exception handling)
try {
    floor1.addUnit("Master Bedroom", 14, 9);
    floor1.addUnit("Office", 8, 12);
    floor2.addUnit("Kitchen", 9, 10);
} catch (InsufficientFloorSpaceException e) {
    e.printStackTrace();
}

// Add floors to blueprint
blueprint.addFloorPlan(floor1);
blueprint.addFloorPlan(floor2);

System.out.println(blueprint);
// Output: 28.6 percents of building blueprint completed (2 out of 7 floors)
```

## Key Concepts

### Unit Equality
Two units are considered equal if:
- Their intended functions are identical (case-sensitive)
- Their areas in square feet are the same (dimensions can differ)

### Floor Equality
Two floors are considered equal if:
- Their maximum capacities are identical
- Their spaces are utilized in the same way (same units, regardless of order)

### Composition Preservation
The `getFloors()` accessor creates deep copies to prevent aliasing and ensure data integrity.

## Exception Handling

The application throws `InsufficientFloorSpaceException` when:
- Attempting to add a unit that would exceed the floor's maximum capacity
- The floor maintains its state when exceptions occur

## Testing

The project includes comprehensive JUnit tests covering:
- Unit creation and measurement conversion
- Unit equality comparisons
- Floor capacity management
- Floor equality with different unit configurations
- Blueprint creation and floor plan management
- Composition preservation in accessors

Run all tests to verify functionality after making changes.

## License

This project is part of an educational exercise in object-oriented programming and design patterns.

## Contributing

This is an educational project. Please follow standard Java coding conventions and ensure all tests pass before submitting changes.