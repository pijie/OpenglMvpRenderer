cmake_minimum_required(VERSION 3.10.2)
project("openrendermvp")

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Werror")

include_directories(${CMAKE_SOURCE_DIR}/glm)


add_library(
        opengl-renderer
        SHARED
        opengl_mvp_renderer.cpp
        stb_image.h
        #        opengl_renderer.cpp
)
add_subdirectory(glm)

find_library(opengl-lib GLESv3)
find_library(egl-lib EGL)
find_library(android-lib android)
find_library(
        log-lib
        log)

target_link_libraries(
        opengl-renderer
        glm_static
        ${log-lib}
        ${opengl-lib}
        ${egl-lib}
        ${android-lib}
)