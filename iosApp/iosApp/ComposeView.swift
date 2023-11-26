//
//  ComposeView.swift
//  iosApp
//
//  Created by Felix kariuki on 25/11/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
    
        Platform_iosKt.MainViewController()
    }
}
