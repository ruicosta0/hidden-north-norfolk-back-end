import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

// extension (added to Project type) to access [libraries] section in libs.versions.toml
val Project.libraries: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")