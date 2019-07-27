# SelectionBottomSheet
[![](https://jitpack.io/v/Ahmed3Elshaer/SelectionBottomSheet.svg)](https://jitpack.io/#Ahmed3Elshaer/SelectionBottomSheet)

SelectionBottomSheet is an Android Library for showing selection options with BottomSheet and RecyclerView instead of the ugly spinner, with easy usage in a few lines of code.

## Demo
![gif](https://github.com/Ahmed3Elshaer/SelectionBottomSheet/blob/master/art/demo.gif)

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
MIT License

Copyright (c) [2019] [Ahmed Elshaer]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
