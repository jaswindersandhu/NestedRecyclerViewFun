package ca.nick.nestedrecyclerviewfun.data

object TheRepository {

    private val _verticalData = mutableListOf<List<Int>>()
    private val _horizontalData = mutableListOf<Int>()

    val verticalData: List<List<Int>> get() = _verticalData

    init {
        for (i in 1..100) {
            _horizontalData.add(i)
        }

        for (i in 1..25) {
            _verticalData.add(_horizontalData.toList())
        }
    }
}