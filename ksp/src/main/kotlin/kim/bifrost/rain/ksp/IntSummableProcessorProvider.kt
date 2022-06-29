package kim.bifrost.rain.ksp

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * kim.bifrost.rain.ksp.IntSummableProcessorProvider
 * ksp-learn
 *
 * @author 寒雨
 * @since 2022/6/28 20:46
 */
class IntSummableProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return IntSummableProcessor(
            options = environment.options,
            codeGenerator = environment.codeGenerator,
            logger = environment.logger
        )
    }
}