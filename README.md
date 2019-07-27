# SelectionBottomSheet
[![](https://jitpack.io/v/Ahmed3Elshaer/SelectionBottomSheet.svg)](https://jitpack.io/#Ahmed3Elshaer/SelectionBottomSheet)

SelectionBottomSheet is an Android Library for showing selection options with BottomSheet and RecyclerView instead of the ugly spinner, with easy usage in a few lines of code.

## Features 
- AndroidX Support.
- Kotlin Based.
- Use your custom model.
- Get Selection callbacks with your custom model.

## Requirements
- min SDK 21
- AndroidX

## Installation

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Ahmed3Elshaer:SelectionBottomSheet:0.1.0'
	}
## Usage

```kotlin 
 //prepare list of your models
        val selectionList =
            mutableListOf(
                SelectionModel("Selection 1", "1"),
                SelectionModel("Selection 2", "2"),
                SelectionModel("Selection 3", "3"),
                SelectionModel("Selection 4", "4")
            )
        //start using the Builder for the BottomSheet
        //You should pass your type of model to the Builder Class first
        //And your activity reference
        SelectionBuilder<SelectionModel>(this)
            //passing the list of models that gonna be shown as the selection list
            .list(selectionList)
            //set the title for the BottomSheet
            .title("Title")
            //this is a lambda expression with the model type that you passed for the Builder
            //and it's required for specifying(returning) which property of your model
            //would be shown as a text for Selections
            .itemBinder { model ->
                model.title
            }
            //shows the BottomSheet and returns a lambda callback that triggers
            //when user selects any item with selected model
            .show {selectedModel ->

            }
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
